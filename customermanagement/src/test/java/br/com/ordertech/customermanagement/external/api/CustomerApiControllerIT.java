package br.com.ordertech.customermanagement.external.api;


import br.com.ordertech.customermanagement.external.dto.CustomerDto;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerApiControllerIT {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class Register {
        @Test
        void shouldRegisterCustomer() {
            CustomerDto customerDto = Util.buildCustomerDto();
            customerDto.setCustomerId(null);

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(customerDto)
                    //        .log().all()
            .when()
                    .post("customer")
            .then()
                    //        .log().all()
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath("schemas/customer.schema.json"));
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldFindCustomerById() {

            when()
                    .get("customer/{id}", 10)
            .then()
                    //        .log().all()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("schemas/customer.schema.json"));
        }

        @Test
        void shouldNotFound() {

            when()
                    .get("customer/{id}", 99)
            .then()
                    .log().all()
                    .statusCode(HttpStatus.NOT_FOUND.value());
        }
    }

    @Nested
    class FindByName {
        @Test
        void shouldFindCustomerByName() {

            when()
                    .get("customer/by-name/{name}", "JOSE DA SILVA")
                    .then()
                    //        .log().all()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("schemas/customerList.schema.json"));
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdateCustomer() {
            CustomerDto customerDto = Util.buildCustomerDto();
            customerDto.setCustomerId(null);
            customerDto.setName("MARIA DA SILVA II");

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(customerDto)
                    //        .log().all()
            .when()
                    .put("customer/{id}", 20)
            .then()
                    //        .log().all()
                    .statusCode(HttpStatus.OK.value())
                    .body(matchesJsonSchemaInClasspath("schemas/customer.schema.json"));
        }

        @Test
        void shouldNotFound() {
            CustomerDto customerDto = Util.buildCustomerDto();
            customerDto.setCustomerId(null);
            customerDto.setName("MARIA DA SILVA II");

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(customerDto)
            //        .log().all()
            .when()
                    .get("customer/{id}", 99)
            .then()
                    .log().all()
                    .statusCode(HttpStatus.NOT_FOUND.value());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDeleteCustomer() {

            when()
                    .delete("customer/{id}", 30)
                    .then()
                    //        .log().all()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }

}
