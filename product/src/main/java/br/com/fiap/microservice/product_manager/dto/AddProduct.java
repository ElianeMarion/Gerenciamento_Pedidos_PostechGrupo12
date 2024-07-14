package br.com.fiap.microservice.product_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct {
    private String name;
    private String description;
    private int QuantityStock;
    private BigDecimal price;
}
