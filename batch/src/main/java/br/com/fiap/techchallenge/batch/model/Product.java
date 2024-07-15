package br.com.fiap.techchallenge.batch.model;

import lombok.*;

import java.math.BigDecimal;

@Data
public class Product {

    private String name;
    private String description;
    private int quantityStock;
    private BigDecimal price;
}
