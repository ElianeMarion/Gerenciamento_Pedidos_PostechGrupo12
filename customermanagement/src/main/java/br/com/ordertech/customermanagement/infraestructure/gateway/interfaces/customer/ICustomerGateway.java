package br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;

import java.util.List;

public interface ICustomerGateway {
    CustomerEntity registerCustomer(CustomerEntity customerEntity);
    CustomerEntity findCustomerById(Long customerId);
    List<CustomerEntity> findCustomerByName(String name);
    CustomerEntity updateCustomer(Long customerId, CustomerEntity customerEntity);
    void deleteCustomer(Long customerId);
}
