package br.com.ordertech.customermanagement.external.dto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ErroApiTest {
    @Nested
    class Create {
        @Test
        void shouldCreateErroApi() {
            ErrorApi errorApi = new ErrorApi("error message");

            assertThat(errorApi)
                    .isNotNull()
                    .isInstanceOf(ErrorApi.class);
        }
    }



    @Nested
    class AddError {
        @Test
        void shouldAddError() {
            ErrorApi errorApi = new ErrorApi("error message");
            errorApi.addError(new FieldError("street", "invalid street"));

            assertThat(errorApi)
                    .isNotNull()
                    .isInstanceOf(ErrorApi.class);
        }
    }

}
