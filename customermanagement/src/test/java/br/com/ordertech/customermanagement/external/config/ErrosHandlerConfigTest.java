package br.com.ordertech.customermanagement.external.config;

import br.com.ordertech.customermanagement.entity.customer.exception.CustomerInvalidException;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;
import br.com.ordertech.customermanagement.external.dto.ErrorApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrosHandlerConfigTest {

    @InjectMocks
    private ErrorsHandlerConfig errorsHandlerConfig;

    private AutoCloseable mocks;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        mocks.close();
    }

    @Nested
    class ExceptionHandler {
        @Test
        void shouldThrowCustomerNotFound() {
            ResponseEntity<ErrorApi> errorResp = errorsHandlerConfig
                    .errorCustomerNotFound(new CustomerNotFoundException());

            assertThat(errorResp).isNotNull().isInstanceOf(ResponseEntity.class)
                    .extracting("statusCode").isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        void shouldThrowArgumentNotValid() {
            // MethodArgumentNotValidException
            /*
            ResponseEntity<ErrorApi> errorResp = errorsHandlerConfig
                    .errorArgumentsNotValid(new MethodArgumentNotValidException(null, null));

            assertThat(errorResp).isNotNull().isInstanceOf(ResponseEntity.class)
                    .extracting("statusCode").isEqualTo(HttpStatus.BAD_REQUEST);

             */
        }
        // CustomerInvalidException
        @Test
        void shouldThrowCustomerInvalid() {
            // CustomerInvalidException
            ResponseEntity<ErrorApi> errorResp = errorsHandlerConfig
                    .errorCustomerInvalid(new CustomerInvalidException("Invalid customer"));

            assertThat(errorResp).isNotNull().isInstanceOf(ResponseEntity.class)
                    .extracting("statusCode").isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
