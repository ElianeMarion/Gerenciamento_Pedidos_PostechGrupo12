package br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;

import java.util.List;

public interface ICustomerGateway {
    CustomerEntity registerCustomer(CustomerEntity customerEntity);
    CustomerEntity findCustomerById(Integer customerId);
    List<CustomerEntity> findCustomerByName(String name);
    CustomerEntity updateCustomer(Integer customerId, CustomerEntity customerEntity);
    void deleteCustomer(Integer customerId);
}
