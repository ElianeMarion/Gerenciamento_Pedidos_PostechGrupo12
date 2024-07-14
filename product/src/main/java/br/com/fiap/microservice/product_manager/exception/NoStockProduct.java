package br.com.fiap.microservice.product_manager.exception;

public class NoStockProduct extends RuntimeException {
    public NoStockProduct(int totalRequired) {
        super(
            """
            The stock no has %s item this product!
            """.formatted(totalRequired)
        );
    }
}
