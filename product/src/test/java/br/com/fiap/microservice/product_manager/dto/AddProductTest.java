package br.com.fiap.microservice.product_manager.dto;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AddProductTest {

    @Test
    void deveRetornarTotalmentePreenchido() {
        // Arrange
        AddProduct addProduct = new AddProduct();

        // Action
        addProduct.setName("Teste");
        addProduct.setPrice(new BigDecimal("150.00"));
        addProduct.setDescription("Add Teste");
        addProduct.setQuantityStock(100);

        // Assert
        Assertions.assertThat(addProduct).isNotNull();
        Assertions.assertThat(addProduct.getName()).isEqualTo("Teste");
        Assertions.assertThat(addProduct.getPrice()).isPositive();
        Assertions.assertThat(addProduct.getDescription()).isEqualTo("Add Teste");
        Assertions.assertThat(addProduct.getQuantityStock()).isPositive();
    }

    @Test
    void deveRetornarTotalmentePreenchidoPeloConstrutor() {
        // Arrange
        AddProduct addProduct = new AddProduct(
    "Teste", "Add Teste", 100, new BigDecimal("150.00")
        );

        // Assert
        Assertions.assertThat(addProduct).isNotNull();
        Assertions.assertThat(addProduct.getName()).isEqualTo("Teste");
        Assertions.assertThat(addProduct.getPrice()).isPositive();
        Assertions.assertThat(addProduct.getDescription()).isEqualTo("Add Teste");
        Assertions.assertThat(addProduct.getQuantityStock()).isPositive();
    }

}