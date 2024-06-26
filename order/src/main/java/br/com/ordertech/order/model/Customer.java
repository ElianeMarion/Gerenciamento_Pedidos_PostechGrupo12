package br.com.ordertech.order.model;

import lombok.Data;

@Data
public class Customer {
    private Integer customerId;
    private String name;
    private AddressRequest address;

    @Override
    public String toString() {
        return "Customer{" +
                "id =" + customerId +
                ", nome ='" + name + '\'' +
                ", address =" + address +
                '}';
    }
}
