package br.com.ordertech.customermanagement.bbd;

import br.com.ordertech.customermanagement.external.dto.CustomerDto;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition {

    private Response response;
    private CustomerDto customerDtoResponse;

    private final String ENDPOINT_API_MENSAGEM = "http://localhost:8080/customer";

    @When("register new customer")
    public CustomerDto register_new_customer() {
        CustomerDto customerDto = Util.buildCustomerDto();
        customerDto.setCustomerId(null);

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customerDto)
                .log().all()
        .when()
                .post(ENDPOINT_API_MENSAGEM);

        return response.then()
                .log().all()
                .extract().as(CustomerDto.class);
    }

    @When("find customer")
    public void find_customer() {
        response = when()
                .get(ENDPOINT_API_MENSAGEM + "/{id}", customerDtoResponse.getCustomerId());
    }

    @When("find customer by name")
    public void find_customer_by_name() {
        response = when()
                .get(ENDPOINT_API_MENSAGEM + "/by-name/{name}", customerDtoResponse.getName());
    }

    @When("update customer")
    public void update_customer() {
        customerDtoResponse.setName("MARIA DA SILVA II");

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customerDtoResponse)
        .when()
                .put(ENDPOINT_API_MENSAGEM + "/{id}", customerDtoResponse.getCustomerId());
    }

    @When("delete customer")
    public void delete_customer() {
        response = when()
                .delete(ENDPOINT_API_MENSAGEM + "/{id}", customerDtoResponse.getCustomerId());
    }

    @Then("customer registered successfully")
    public void customer_registered_successfully() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Then("customer updated successfully")
    public void customer_updated_successfully() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Then("customer found")
    public void customer_found() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Then("customer is shown")
    public void customer_is_shown() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/customer.schema.json"));
    }

    @Then("list of customer is shown")
    public void list_of_customer_is_shown() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/customerList.schema.json"));
    }

    @Then("NoContent")
    public void no_content() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Given("customer was registered")
    public void customer_was_registered() {
        customerDtoResponse = register_new_customer();
    }

}
