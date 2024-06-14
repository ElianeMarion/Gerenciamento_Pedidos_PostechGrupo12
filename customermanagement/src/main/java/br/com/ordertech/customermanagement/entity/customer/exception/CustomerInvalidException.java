package br.com.ordertech.customermanagement.entity.customer.exception;

public class CustomerInvalidException extends RuntimeException {
    public CustomerInvalidException(String message) {
        super(message);
    }
}
