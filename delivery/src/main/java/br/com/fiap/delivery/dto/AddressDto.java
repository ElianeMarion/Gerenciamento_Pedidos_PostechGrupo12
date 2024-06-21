package br.com.fiap.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    @Schema(name = "addressID",
            type = "Long",
            description = "id do endereço",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Long addressID;

    @Schema(name = "street",
            type = "String",
            description = "nome da rua",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotEmpty
    private String street;

    @Schema(name = "number",
            type = "Integer",
            description = "número",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Integer number;

    @Schema(name = "complement",
            type = "String",
            description = "complemento",
            requiredMode = Schema.RequiredMode.AUTO)
    private String complement;

    @Schema(name = "city",
            type = "String",
            description = "cidade",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotEmpty
    private String city;

    @Schema(name = "state",
            type = "String",
            description = "uf",
            requiredMode = Schema.RequiredMode.AUTO,
            example = "SP")
    @NotEmpty
    private String state;

    @Schema(name = "zipCode",
            type = "String",
            description = "cep somente números",
            requiredMode = Schema.RequiredMode.AUTO,
            example = "06826272")
    @NotEmpty
    private String zipCode;

    @Schema(name = "subSector",
            type = "Integer",
            description = "sub-setor, corresponde ao quarto dígito do cep",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Integer subSector;

}
