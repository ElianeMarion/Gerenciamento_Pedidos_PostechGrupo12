package br.com.fiap.delivery.dto.api;

import br.com.fiap.delivery.dto.api.ErrorApi;
import br.com.fiap.delivery.dto.api.FieldError;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ErrorApiTest {

    @Test
    void testInstance() {
        ErrorApi error =new ErrorApi("erro");
        error.addError(new FieldError("name", "não pode ser nulo"));
        assertThat(error.getMessage()).isEqualTo("erro");
        assertThat(error.getFieldErrors()).isNotNull().asList().element(0).isInstanceOf(FieldError.class);
    }
}
