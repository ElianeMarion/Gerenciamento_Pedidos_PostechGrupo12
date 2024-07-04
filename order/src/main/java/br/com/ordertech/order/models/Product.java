package br.com.ordertech.order.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productID;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantityStock;
}
