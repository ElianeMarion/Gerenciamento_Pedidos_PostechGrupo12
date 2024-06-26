package br.com.ordertech.order.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long productId;
    private int QuantityRequired;
    private BigDecimal price;
}
