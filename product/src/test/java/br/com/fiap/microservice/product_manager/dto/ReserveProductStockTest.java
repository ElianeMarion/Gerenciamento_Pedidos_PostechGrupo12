package br.com.fiap.microservice.product_manager.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ReserveProductStockTest {
    @Test
    void deveRetornarTotalmentePreenchido() {
        // Arrange
        ReserveProductStock reserve = new ReserveProductStock();

        // Action
        reserve.setProductId(1L);
        reserve.setQuantityRequired(100);

        // Assert
        Assertions.assertThat(reserve).isNotNull();
        Assertions.assertThat(reserve.getProductId()).isEqualTo(1L);
        Assertions.assertThat(reserve.getQuantityRequired()).isPositive();
    }

    @Test
    void deveRetornarTotalmentePreenchidoPeloConstrutor() {
        // Arrange
        ReserveProductStock product = new ReserveProductStock(1L, 100);

        // Assert
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getProductId()).isEqualTo(1L);
        Assertions.assertThat(product.getQuantityRequired()).isPositive();
        Assertions.assertThat(product.getQuantityRequired()).isEqualTo(100);
    }
}