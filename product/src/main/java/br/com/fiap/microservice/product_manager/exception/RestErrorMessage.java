package br.com.fiap.microservice.product_manager.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestErrorMessage {
    private HttpStatus status;
    private String message;
}
