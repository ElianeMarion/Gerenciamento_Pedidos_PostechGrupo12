package br.com.fiap.delivery.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorApi {
    @Schema(name = "message",
            type = "String",
            description = "Mensagem de erro"
    )
    private String message;

    @Schema(name = "errorsValidation",
            type = "Lista",
            format="List<ErrorValidation>",
            description = "Lista com parâmetros e respectivos erros de request"
    )
    private List<FieldError> fieldErrors = new ArrayList<>();


    public ErrorApi(String message) {
        this.message = message;
    }

    public void addError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }

}
