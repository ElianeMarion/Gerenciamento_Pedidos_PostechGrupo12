package br.com.ordertech.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDto {

    @Schema(name = "orderLineID",
            type = "Long",
            description = "ID do item do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Long orderLineID;

    @Schema(name = "product",
            type = "ProductDto",
            description = "produto do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private ProductDto product;

    @Schema(name = "quantity",
            type = "Integer",
            description = "quantidade",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Integer quantity;

    @Schema(name = "price",
            type = "BigDecimal",
            description = "preço total do item do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private BigDecimal price;

}
