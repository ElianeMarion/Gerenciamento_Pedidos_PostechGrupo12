package br.com.fiap.microservice.product_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<RestErrorMessage> productNotFound(ProductNotFound ex) {
        RestErrorMessage restErrorMessage = new RestErrorMessage();
        restErrorMessage.setStatus(HttpStatus.NOT_FOUND);
        restErrorMessage.setMessage(ex.getMessage());
        return ResponseEntity.status(restErrorMessage.getStatus()).body(restErrorMessage);
    }
}
