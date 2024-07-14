package br.com.fiap.microservice.product_manager.exception;

public class MaximumNumberOfItemsHigherThanAllowed extends RuntimeException {
    public MaximumNumberOfItemsHigherThanAllowed() {
        super("Maximum number of items exceed that allowed for stock!");
    }
}
