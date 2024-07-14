package br.com.fiap.microservice.product_manager.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound() {
        super("The product not found!");
    }
}
