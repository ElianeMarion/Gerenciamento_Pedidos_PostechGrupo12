package br.com.ordertech.order.dto;


import br.com.ordertech.order.models.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
