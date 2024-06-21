package br.com.fiap.delivery.controller;

import br.com.fiap.delivery.configdb.EnableMongoTestServer;
import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.enums.CourierStatus;
import br.com.fiap.delivery.repository.CourierRepository;
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

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableMongoTestServer
@EnableMongoRepositories(basePackageClasses = {CourierRepository.class})
public class CourierControllerIT {

    @LocalServerPort
    private int port;

    private static final String PATH = "/delivery/courier";

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Nested
    class CreateCouriser {

        @Test
        void testSaveCourierSuccess() {
            saveCourier(new CourierDto("João"))
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/courier.save.response.schema.json"));
        }

        @Test
        void testSaveCourierBadRequest() {
            saveCourier(new CourierDto())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"));
        }

    }


    @Nested
    class FindCouriser {

        @Test
        void testFindCourierSuccess() {
            saveCourier(new CourierDto("João"));
            Object resp = given()
                    .get(PATH)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .extract().body().as(List.class);
            assertThat(resp).isNotNull().asList().size().isPositive();
        }

    }

    @Nested
    class UpdateCouriser {

        @Test
        void testUpdateCourierSuccess() {
            CourierDto courier = saveCourier(new CourierDto("João"))
                    .extract().body().as(CourierDto.class);
            CourierDto courierRefresh = new CourierDto();
            courierRefresh.setStatus(CourierStatus.INACTIVE.getDesc());
            given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(courierRefresh)
                .put(PATH + "/" + courier.getCourierID())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("jsonschemas/courier.save.response.schema.json"));
        }

        @Test
        void testUpdateCourierBadRequest() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(new CourierDto())
                    .put(PATH + "/123")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"));
        }

    }



    private ValidatableResponse saveCourier(CourierDto courierDto) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(courierDto)
                .post(PATH)
                .then();
    }

}
