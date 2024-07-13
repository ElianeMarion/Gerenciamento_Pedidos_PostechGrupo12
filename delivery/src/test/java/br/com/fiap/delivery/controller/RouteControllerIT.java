package br.com.fiap.delivery.controller;


import br.com.fiap.delivery.dto.route.Route;
import br.com.fiap.delivery.dto.route.RouteResponse;
import br.com.fiap.delivery.utils.Util;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WireMockTest(httpPort = 8000)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RouteControllerIT {


    @LocalServerPort
    private int port;

    private static final String PATH = "/delivery/route";


    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testFindRouteSuccess() throws Exception {
        WireMock.stubFor(WireMock.post("/directions/v2:computeRoutes")
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(Util.serialize(Util.Objects.buildRouteResponse()))));

        RouteResponse resp = given()
                .queryParam("addressOrigin", "rua leblon")
                .queryParam("addressDestination", "rua Urca")
                .when()
                .get(PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().as(RouteResponse.class);

        assertThat(resp).isNotNull().isInstanceOf(RouteResponse.class);
        assertThat(resp.getRoutes()).isNotNull()
                .asList().element(0).isNotNull().isInstanceOf(Route.class);
    }

    @Test
    void testFindRouteBadRequest() {
        given()
                .queryParam("addressOrigin", "rua leblon")
                .when()
                .get(PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("jsonschemas/error.schema.json"));
    }

}
