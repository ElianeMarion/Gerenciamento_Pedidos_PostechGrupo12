package br.com.ordertech.order.models;

import lombok.Data;

@Data
public class Customer {
    private Long customerId;
    private String name;
    private String cpf;
    private String phoneNumber;
    private Address address;
}
