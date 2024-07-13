package br.com.fiap.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {


    @Schema(name = "productID",
            type = "Long",
            description = "id do produto",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Long productID;

    @Schema(name = "name",
            type = "String",
            description = "nome do produto",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private String name;

    @Schema(name = "description",
            type = "String",
            description = "descrição do produto",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private String description;

    @Schema(name = "price",
            type = "BigDecimal",
            description = "preço do produto",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private BigDecimal price;

    @Schema(name = "quantityStock",
            type = "Integer",
            description = "quantidade em estoque",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Integer quantityStock;

}
