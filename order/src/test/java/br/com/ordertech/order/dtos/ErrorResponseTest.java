package br.com.ordertech.order.dtos;

import br.com.ordertech.order.dto.ErrorResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorResponseTest {

    @Test
    public void testNoArgsConstructor() {
        ErrorResponse errorResponse = new ErrorResponse();
        assertThat(errorResponse).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        List<String> errors = Arrays.asList("Error 1", "Error 2");
        ErrorResponse errorResponse = new ErrorResponse("Error message", errors);

        assertThat(errorResponse.getMessage()).isEqualTo("Error message");
        assertThat(errorResponse.getErrors()).containsExactly("Error 1", "Error 2");
    }

    @Test
    public void testSettersAndGetters() {
        List<String> errors = Arrays.asList("Error 1", "Error 2");
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage("Error message");
        errorResponse.setErrors(errors);

        assertThat(errorResponse.getMessage()).isEqualTo("Error message");
        assertThat(errorResponse.getErrors()).containsExactly("Error 1", "Error 2");
    }
}
