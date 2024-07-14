package br.com.fiap.microservice.product_manager.exception;

public class ProductFound extends RuntimeException {
    public ProductFound() {
        super("The product has been exist in repository.");
    }
}
