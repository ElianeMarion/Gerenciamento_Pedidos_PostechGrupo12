package br.com.ordertech.customermanagement.external.db;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;
import br.com.ordertech.customermanagement.external.model.AddressModel;
import br.com.ordertech.customermanagement.external.model.CustomerModel;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.IDataBase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseJpa implements IDataBase {
    private CustomerRepository customerRepository;

    @Autowired
    public DataBaseJpa(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity registerCustomer(CustomerEntity customerEntity) {
        CustomerModel customerModel = new CustomerModel(customerEntity);
        customerModel = customerRepository.save(customerModel);
        customerEntity.setCustomerId(customerModel.getCustomerId());
        return customerEntity;
    }

    @Override
    public CustomerEntity findCustomerById(Integer customerId) {
        CustomerModel customerModel = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException());
        return customerModel.toEntity();
    }

    @Override
    public List<CustomerEntity> findCustomerByName(String name) {
        List<CustomerModel> customerModels = customerRepository.findByName(name);
        return customerModels.stream().map(CustomerModel::toEntity).toList();
    }

    @Override
    public CustomerEntity updateCustomer(Integer customerId, CustomerEntity customerEntity) {
        try {
            CustomerModel customerModelUpdated = new CustomerModel(customerEntity);
            AddressModel addressModelUpdated = customerModelUpdated.getAddressModel();

            CustomerModel customerModel = customerRepository.getReferenceById(customerId);
            customerModel.setName(customerModelUpdated.getName());
            customerModel.setCpf(customerModelUpdated.getCpf());
            customerModel.setPhoneNumber(customerModelUpdated.getPhoneNumber());

            AddressModel addressModel = customerModel.getAddressModel();
            addressModel.setStreet(addressModelUpdated.getStreet());
            addressModel.setNumber(addressModelUpdated.getNumber());
            addressModel.setComplement(addressModel.getComplement());
            addressModel.setCity(addressModel.getCity());
            addressModel.setState(addressModelUpdated.getState());
            addressModel.setZipcode(addressModelUpdated.getZipcode());
            addressModel.setSubSector(addressModelUpdated.getSubSector());

            customerModel = customerRepository.save(customerModel);
            return  customerModel.toEntity();
        } catch (EntityNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

}
