package br.com.fiap.delivery.controller.handler;

import br.com.fiap.delivery.dto.api.ErrorApi;
import br.com.fiap.delivery.dto.api.FieldError;
import br.com.fiap.delivery.exception.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class ErrorsHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> badRequest(MethodArgumentNotValidException ex) {
        ErrorApi errorApi = new ErrorApi("Argumentos inválidos");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorApi.addError(new FieldError(error.getField(),
                    messageSource.getMessage(error, new Locale("pt","BR"))));
        });
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorApi> badRequest(MissingServletRequestParameterException ex) {
        ErrorApi errorApi = new ErrorApi("Requisição inválida");
        errorApi.addError(new FieldError(ex.getParameterType(), "Argumento obrigatório."));
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(DeliveryException.class)
    public ResponseEntity<ErrorApi> conflict(DeliveryException ex) {
        ErrorApi errorApi = new ErrorApi(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorApi);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> badRequest(Exception ex) {
        return ResponseEntity.internalServerError().build();
    }

}
