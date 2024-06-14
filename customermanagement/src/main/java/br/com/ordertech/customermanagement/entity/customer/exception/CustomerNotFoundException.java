package br.com.ordertech.customermanagement.entity.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Cliente não localizado");
    }
}
