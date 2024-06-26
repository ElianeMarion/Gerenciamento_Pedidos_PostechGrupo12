package br.com.ordertech.order.exceptions;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String clienteNãoRetornado) {
        super(clienteNãoRetornado);
    }
}
