package br.com.ordertech.customermanagement.infraestructure.gateway.impl.customer;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.ICustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.IDataBase;

import java.util.List;

public class CustomerGateway implements ICustomerGateway {
    private final IDataBase dataBase;

    public CustomerGateway(IDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public CustomerEntity registerCustomer(CustomerEntity customerEntity) {
        return this.dataBase.registerCustomer(customerEntity);
    }

    @Override
    public CustomerEntity findCustomerById(Long customerId) {
        return this.dataBase.findCustomerById(customerId);
    }

    @Override
    public List<CustomerEntity> findCustomerByName(String name) {
        return this.dataBase.findCustomerByName(name);
    }

    public CustomerEntity updateCustomer(Long customerId, CustomerEntity customerEntity) {
        return this.dataBase.updateCustomer(customerId, customerEntity);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        this.dataBase.deleteCustomer(customerId);
    }
}
