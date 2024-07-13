package br.com.ordertech.order.model;

import br.com.ordertech.order.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
public class ProductTest {

    @Test
    public void testProductConstructorAndGetters() {
        Long productID = 1L;
        String name = "Product Name";
        String description = "Product Description";
        BigDecimal price = BigDecimal.valueOf(99.99);
        Integer quantityStock = 100;

        Product product = new Product(productID, name, description, price, quantityStock);

        assertThat(product.getProductID()).isEqualTo(productID);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getQuantityStock()).isEqualTo(quantityStock);
    }

    @Test
    public void testSetters() {
        Product product = new Product();

        Long productID = 1L;
        String name = "Product Name";
        String description = "Product Description";
        BigDecimal price = BigDecimal.valueOf(99.99);
        Integer quantityStock = 100;

        product.setProductID(productID);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantityStock(quantityStock);

        assertThat(product.getProductID()).isEqualTo(productID);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getQuantityStock()).isEqualTo(quantityStock);
    }
}
