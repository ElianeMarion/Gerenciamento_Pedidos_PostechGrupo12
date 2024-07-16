package br.com.ordertech.order.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class ProductDto {

    @NotNull
    @JsonAlias({"productId", "productID"})
    private Long productID;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantityStock;

    public ProductDto(Long productID, Integer quantityStock) {
        this.productID = productID;
        this.quantityStock = quantityStock;
    }
}
