package br.com.fiap.delivery.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldError {


    @Schema(name = "field",
            type = "String",
            description = "Parâmetro de request"
    )
    private String field;


    @Schema(name = "message",
            type = "String",
            description = "Descrição do erro relacionaro ao parâmetro"
    )
    private String message;

}
