package br.com.ordertech.order.controller;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.OrderDto;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 8000)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIT2 {

    @LocalServerPort
    private int port;

    private static final String PATH = "/orders";

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class SaveOrder {

        @Test
        void testShouldPermitSaveOrder() {
            WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/product/reserve"))
                    .withHeader("Content-Type", equalTo("application/json"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(OrderHelper.serialize(OrderHelper.buildProduct()))));

            OrderDto resp = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(OrderHelper.createOrder())
                    .when()
                    .post(PATH)
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/order.save.response.schema.json"))
                    .extract().body().as(OrderDto.class);

            assertThat(resp.getOrderId()).isNotZero().isInstanceOf(Long.class);
        }

        @Test
        void testShouldBadRequestSaveOrder() {
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(OrderHelper.createInvalidOrder()) // Assumindo que createInvalidOrder() retorna um pedido inválido
                    .when()
                    .post(PATH)
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"));

        }
    }
}
