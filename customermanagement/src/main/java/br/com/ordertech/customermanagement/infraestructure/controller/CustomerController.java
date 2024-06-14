package br.com.ordertech.customermanagement.infraestructure.controller;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.infraestructure.gateway.impl.customer.CustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.ICustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.IDataBase;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.AddressRecord;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
import br.com.ordertech.customermanagement.usecase.CustomerUseCase;

import java.util.List;

public class CustomerController {
    public CustomerEntity register (CustomerRecord customerRecord, IDataBase dataBase) {
        ICustomerGateway customerGateway = new CustomerGateway(dataBase);
        AddressRecord addressRecord = customerRecord.address();
        Address address = new Address(addressRecord.street(), addressRecord.number(),
                addressRecord.complement(), addressRecord.city(), addressRecord.state(),
                addressRecord.zipcode());
        CustomerEntity customerEntity = CustomerUseCase.registerCustomer(
                customerRecord.name(), customerRecord.cpf(), customerRecord.phoneNumber(),
                address);
        customerEntity = customerGateway.registerCustomer(customerEntity);
        return customerEntity;
    }

    public CustomerEntity findById(Integer customerId, IDataBase dataBase) {
        ICustomerGateway customerGateway = new CustomerGateway(dataBase);
        customerId = CustomerUseCase.findCustomerById(customerId);
        CustomerEntity customerEntity = customerGateway.findCustomerById(customerId);
        return customerEntity;
    }

    public List<CustomerEntity> findByName(String name, IDataBase dataBase) {
        name = CustomerUseCase.findCustomerByName(name);
        List<CustomerEntity> customers = dataBase.findCustomerByName(name);
        return CustomerUseCase.locateCustomers(customers);
    }

    public CustomerEntity update (Integer customerId, CustomerRecord customerRecord, IDataBase dataBase) {
        ICustomerGateway customerGateway = new CustomerGateway(dataBase);
        AddressRecord addressRecord = customerRecord.address();
        Address address = new Address(addressRecord.street(), addressRecord.number(),
                addressRecord.complement(), addressRecord.city(), addressRecord.state(),
                addressRecord.zipcode());
        CustomerEntity customerEntity = CustomerUseCase.updateCustomer(customerId, customerRecord.name(),
                customerRecord.cpf(), customerRecord.phoneNumber(), address);
        customerEntity = customerGateway.updateCustomer(customerId, customerEntity);
        return customerEntity;
    }

    public void delete(Integer customerId, IDataBase dataBase) {
        ICustomerGateway customerGateway = new CustomerGateway(dataBase);
        customerId = CustomerUseCase.deleteCustomer(customerId);
        customerGateway.deleteCustomer(customerId);
    }
}
