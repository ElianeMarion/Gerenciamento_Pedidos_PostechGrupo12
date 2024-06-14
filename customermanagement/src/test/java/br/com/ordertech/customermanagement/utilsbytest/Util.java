package br.com.ordertech.customermanagement.utilsbytest;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.external.dto.AddressDto;
import br.com.ordertech.customermanagement.external.dto.CustomerDto;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.AddressRecord;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Util {

    public static Address buildAddress() {
        String street = "Rua das Flores";
        Integer number = Integer.valueOf(132);
        String complement = "Bloco E apto 42";
        String city = "Valinhos";
        String state = "São Paulo";
        Integer zipcode = 6020030;

        return new Address(street, number, complement, city, state, zipcode);
    }

    public static CustomerEntity buildCustomerEntity() {
        String name = "João";
        Integer cpf = 234510990;
        Integer phoneNumber = 912349876;

        return new CustomerEntity(name, cpf, phoneNumber);
    }

    public static CustomerEntity buildCustomerEntityFull() {
        Address address = Util.buildAddress();
        CustomerEntity customerEntity = Util.buildCustomerEntity();

        customerEntity.setAddress(address);
        customerEntity.setCustomerId(10);

        return customerEntity;
    }

    public static AddressRecord buildAddressRecord() {
        String street = "Rua das Flores";
        Integer number = Integer.valueOf(132);
        String complement = "Bloco E apto 42";
        String city = "Valinhos";
        String state = "São Paulo";
        Integer zipcode = 6020030;
        Integer subSector = 6;

        return new AddressRecord(street, number, complement, city, state,
                zipcode, subSector);
    }
    public static CustomerRecord buildCustomerRecord() {
        String name = "João";
        Integer cpf = 234510990;
        Integer phoneNumber = 912349876;
        AddressRecord addressRecord = buildAddressRecord();

        return new CustomerRecord(name, cpf, phoneNumber, addressRecord);
    }

    public static CustomerDto buildCustomerDto() {
        return new CustomerDto(Util.buildCustomerEntityFull());

    }

    public static String asJsonString(final Object object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(object);
    }



}
