package br.com.ordertech.order.dtos;

import br.com.ordertech.order.dto.UpdateProductStock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class UpdateProductStockTest {
    @Test
    public void testNoArgsConstructor() {
        UpdateProductStock updateProductStock = new UpdateProductStock();
        assertThat(updateProductStock).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        UpdateProductStock updateProductStock = new UpdateProductStock(1L, 10);

        assertThat(updateProductStock.getProductId()).isEqualTo(1L);
        assertThat(updateProductStock.getAdditionalStock()).isEqualTo(10);
    }

    @Test
    public void testSettersAndGetters() {
        UpdateProductStock updateProductStock = new UpdateProductStock();

        updateProductStock.setProductId(2L);
        updateProductStock.setAdditionalStock(5);

        assertThat(updateProductStock.getProductId()).isEqualTo(2L);
        assertThat(updateProductStock.getAdditionalStock()).isEqualTo(5);
    }
}
