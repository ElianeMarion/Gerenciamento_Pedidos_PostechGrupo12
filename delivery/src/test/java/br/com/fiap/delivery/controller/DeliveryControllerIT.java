package br.com.fiap.delivery.controller;

import br.com.fiap.delivery.configdb.EnableMongoTestServer;
import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import br.com.fiap.delivery.enums.Status;
import br.com.fiap.delivery.repository.DeliveryRepository;
import br.com.fiap.delivery.utils.Util;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableMongoTestServer
@EnableMongoRepositories(basePackageClasses = {DeliveryRepository.class})
class DeliveryControllerIT {


    @LocalServerPort
    private int port;

    private static final String PATH = "/delivery";

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class SaveDelivery {

        @Test
        void testShouldPermitSaveDelivery() {
            OrderDeliveryResponse resp = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(Util.Objects.buildOrderDeliveryFull())
                    .when()
                    .post(PATH)
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/delivery.save.response.schema.json"))
                    .extract().body().as(OrderDeliveryResponse.class);
            assertThat(resp.getDeliveryID()).isNotBlank().isInstanceOf(String.class);
        }

        @Test
        void testShouldBadRequestSaveDelivery() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(new OrderDelivery())
                    .when()
                    .post(PATH)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"));
        }

        public static OrderDeliveryResponse save() {
            return given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(Util.Objects.buildOrderDeliveryFull())
                    .when()
                    .post(PATH)
                    .then()
                    .extract().body().as(OrderDeliveryResponse.class);
        }

    }

    @Nested
    class FindDelivery {

        @Test
        void testFindDeliveryWhenWaitingOfDelivery() {
            SaveDelivery.save();
            given()
                    .queryParam("subSector", "2")
                    .get(PATH)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/delivery.findlist.response.schema.json"));
        }

    }


    @Nested
    class UpdateDelivery {

        @Test
        void testUpdateSetCourier() {
            OrderDeliveryResponse orderDelivery = SaveDelivery.save();
            CourierDto courierDto = saveCourier();
            given()
                    .queryParam("deliveryID", orderDelivery.getDeliveryID())
                    .queryParam("courierID", courierDto.getCourierID())
                    .put(PATH)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/delivery.save.response.schema.json"));
        }

        @Test
        void testUpdateSetCourierCourierNotFound() {
            given()
                    .queryParam("deliveryID", "123Delivery")
                    .queryParam("courierID", "123Courier")
                    .put(PATH)
                    .then()
                    .statusCode(HttpStatus.CONFLICT.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"))
                    .body("$", hasKey("message"))
                    .body("message", equalTo("Entregador não localizado."));
        }

        @Test
        void testUpdateSetCourierCourierBadRequest() {
            given()
                    .queryParam("deliveryID", "123Delivery")
                    .put(PATH)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"));
        }

        @Test
        void testUpdateStatusLeft() {
            OrderDeliveryResponse orderDelivery = SaveDelivery.save();
            CourierDto courierDto = saveCourier();
            setCourier(orderDelivery.getDeliveryID(), courierDto.getCourierID());
            Object resp = given()
                    .put(PATH + "/left/" + courierDto.getCourierID())
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .extract().body().as(List.class);
            assertThat(resp).asList().hasSize(1);
        }

        @Test
        void testUpdateStatusCompleted() {
            OrderDeliveryResponse orderDelivery = saveWithStatus(Status.WAITING_DELIVERY.getDesc());
            CourierDto courierDto = saveCourier();
            setCourier(orderDelivery.getDeliveryID(), courierDto.getCourierID());
            deliveryLeft(courierDto.getCourierID());
            given()
                    .put(PATH + "/completed/" + orderDelivery.getDeliveryID())
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/delivery.save.response.schema.json"));
        }

    }



    private static CourierDto saveCourier() {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new CourierDto("João"))
                .post(PATH + "/courier")
                .then()
                .extract().body().as(CourierDto.class);
    }

    private static OrderDeliveryResponse setCourier(String deliveryID, String courierID) {
        return given()
                .queryParam("deliveryID", deliveryID)
                .queryParam("courierID", courierID)
                .put(PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().as(OrderDeliveryResponse.class);
    }

    public static OrderDeliveryResponse saveWithStatus(String status) {
        OrderDelivery orderDelivery = Util.Objects.buildOrderDeliveryFull();
        orderDelivery.getOrder().setStatus(status);
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderDelivery)
                .when()
                .post(PATH)
                .then()
                .extract().body().as(OrderDeliveryResponse.class);
    }

    public static ValidatableResponse deliveryLeft(String courierID) {
        return given()
                .put(PATH + "/left/" + courierID)
                .then();
    }

}

