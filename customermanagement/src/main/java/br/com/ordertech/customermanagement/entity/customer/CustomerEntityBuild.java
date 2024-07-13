package br.com.ordertech.customermanagement.entity.customer;

import br.com.ordertech.customermanagement.entity.customer.exception.CustomerInvalidException;

import java.util.Optional;

public class CustomerEntityBuild {
    private Optional<CustomerEntity> customerEntity = Optional.ofNullable(null);

    public CustomerEntityBuild addInfos(String name, String cpf, String phoneNumber) {
        this.customerEntity = Optional.of(new CustomerEntity(name, cpf, phoneNumber));
        return this;
    }

    public CustomerEntityBuild addAddress(Address address) {
        this.customerEntity.ifPresent(c -> c.setAddress(address));
        return this;
    }

    public CustomerEntity build() {
        return customerEntity.orElseThrow(() -> new CustomerInvalidException("Falha ao criar novo cliente"));
    }
}
