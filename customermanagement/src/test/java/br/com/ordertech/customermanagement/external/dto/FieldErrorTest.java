package br.com.ordertech.customermanagement.external.dto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldErrorTest {

    @Nested
    class Create {
        @Test
        void shouldCreateFieldError() {
            FieldError fieldError = new FieldError("street", "invalid street");

            assertThat(fieldError)
                    .isNotNull()
                    .isInstanceOf(FieldError.class);
            assertThat(fieldError.getField()).isEqualTo("street");
            assertThat(fieldError.getMessage()).isEqualTo("invalid street");
        }
    }

}
