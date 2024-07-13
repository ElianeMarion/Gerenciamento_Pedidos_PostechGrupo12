package br.com.ordertech.order.dto;


import br.com.ordertech.order.models.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@Jacksonized
public class OrderLineDto {

    @NotNull
    private Long orderLineID;

    @NotNull
    @Valid
    private ProductDto product;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;
}
