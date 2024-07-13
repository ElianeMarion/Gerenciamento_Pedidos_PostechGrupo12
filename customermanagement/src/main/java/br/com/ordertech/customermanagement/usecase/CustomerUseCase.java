package br.com.ordertech.customermanagement.usecase;

import br.com.ordertech.customermanagement.entity.EntityUtil;
import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntityBuild;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerInvalidException;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;

import java.util.List;
import java.util.Optional;

public class CustomerUseCase {
    public static CustomerEntity registerCustomer(String name, String cpf, String phoneNumber,
                                                  Address address) {
        validAddress(address);
        return new CustomerEntityBuild()
                .addInfos(name, cpf, phoneNumber)
                .addAddress(address)
                .build();
    }

    public static Long findCustomerById(Long customerId) {
        return EntityUtil.isNull(customerId, "Id do cliente é obrigatório");
    }

    public static String findCustomerByName(String name) {
        return validParam(name, 2,
                "Nome inválido");
    }

    public static CustomerEntity updateCustomer(Long customerId, String name, String cpf,
                                                String phoneNumber, Address address) {
        EntityUtil.isNull(customerId, "Id do cliente é obrigatório");
        CustomerEntity customerEntity = new CustomerEntityBuild()
                .addInfos(name, cpf, phoneNumber)
                .addAddress(address)
                .build();
        customerEntity.setCustomerId(customerId);
        return customerEntity;
    }

    public static Long deleteCustomer(Long customerId) {
        return EntityUtil.isNull(customerId, "Id do cliente é obrigatório");
    }

    public static List<CustomerEntity> locateCustomers(List<CustomerEntity> customers) {
        return Optional.ofNullable(customers)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CustomerNotFoundException());
    }

    private static String validParam(String value, int size, String message) {
        return Optional.ofNullable(value)
                .map(String::trim)
                .filter(v -> !v.isBlank())
                .filter(v -> v.length() >= size)
                .orElseThrow(() -> new CustomerInvalidException(message));
    }

    private static void validAddress(Address address) {
        EntityUtil.isNull(address, "Endereço é obrigatório");
    }

}
