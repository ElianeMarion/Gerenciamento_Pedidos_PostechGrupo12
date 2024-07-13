package br.com.fiap.delivery.dto.api;

import br.com.fiap.delivery.dto.api.FieldError;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FieldErrorTest {

    @Test
    void testInstance() {
        FieldError fieldError = new FieldError("name", "não pode ser nulo");
        assertThat(fieldError.getField()).isEqualTo("name");
        assertThat(fieldError.getMessage()).isEqualTo("não pode ser nulo");
    }

}
