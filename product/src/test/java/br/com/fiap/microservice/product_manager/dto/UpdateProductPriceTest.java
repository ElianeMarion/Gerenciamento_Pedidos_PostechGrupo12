package br.com.fiap.microservice.product_manager.dto;

import org.assertj.core.api.Assertions;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UpdateProductPriceTest {
    @Test
    void deveRetornarTotalmentePreenchido() {
        // Arrange
        UpdateProductPrice productPrice = new UpdateProductPrice();

        // Action
        productPrice.setProductId(1L);
        productPrice.setPrice(new BigDecimal("100"));

        // Assert
        Assertions.assertThat(productPrice).isNotNull();
        Assertions.assertThat(productPrice.getProductId()).isEqualTo(1L);
        Assertions.assertThat(productPrice.getPrice()).isPositive();
    }

    @Test
    void deveRetornarTotalmentePreenchidoPeloConstrutor() {
        // Arrange
        UpdateProductPrice productPrice = new UpdateProductPrice(1L, new BigDecimal("100"));

        // Assert
        Assertions.assertThat(productPrice).isNotNull();
        Assertions.assertThat(productPrice.getProductId()).isEqualTo(1L);
        Assertions.assertThat(productPrice.getPrice()).isPositive();
        Assertions.assertThat(productPrice.getPrice()).isEqualTo(BigDecimal.valueOf(100));
    }
}