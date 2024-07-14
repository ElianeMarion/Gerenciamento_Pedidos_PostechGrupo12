package br.com.fiap.microservice.product_manager.model;

import br.com.fiap.microservice.product_manager.dto.AddProduct;
import br.com.fiap.microservice.product_manager.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ProductTest {

    @Mock
    private ProductRepository productRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirListarProdutos() {
        // Arrange
        var list = new ArrayList<Product>();
        var product = productSaved();
        list.add(product);

        when(productRepository.findAll()).thenReturn(list);

       // Action
        var products = productRepository.findAll();

       // Assert
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(products).hasSize(1);
        Assertions.assertThat(products.get(0).getName()).isEqualTo(product.getName());
        Assertions.assertThat(products.get(0).getDescription()).isEqualTo(product.getDescription());
        Assertions.assertThat(products.get(0).getPrice()).isEqualTo(product.getPrice());
        verify(productRepository, times(1)).findAll();

    }

    @Test
    void devePermitirIncluirUmProduto() {
        // Arrange
        var product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        // Action
        var productResult = productRepository.save(product);

        // Assert
        Assertions.assertThat(productResult).isNotNull();
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void devePermitirAlterarUmProduto() {
        // Arrange
        var product = productSaved();
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // Action
        product = productRepository.findById(product.getProductId()).orElseThrow();
        product.setQuantityStock(200);
        product = productRepository.save(product);

        // Assert
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getQuantityStock() == 200).isTrue();

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    private Product productSaved() {
        return Product
                .builder()
                .productId(1L)
                .name("Product 1")
                .price(new BigDecimal("1.00"))
                .description("Description product 1")
                .QuantityStock(2)
                .build();
    }

}