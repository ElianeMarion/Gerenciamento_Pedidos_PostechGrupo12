package br.com.fiap.microservice.product_manager.exception;

public class IncrementalStockValueInvalid extends RuntimeException {
    public IncrementalStockValueInvalid() {
        super("The incremental value for stock is invalid!");
    }
}
