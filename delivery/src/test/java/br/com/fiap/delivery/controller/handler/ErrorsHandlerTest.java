package br.com.fiap.delivery.controller.handler;

import br.com.fiap.delivery.dto.api.ErrorApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ErrorsHandlerTest {

    @InjectMocks
    private ErrorsHandler errorsHandler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testErrorGlobal() {
        ResponseEntity<ErrorApi> resp = errorsHandler
                .badRequest(new RuntimeException("error global"));
        assertThat(resp).isNotNull();
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
