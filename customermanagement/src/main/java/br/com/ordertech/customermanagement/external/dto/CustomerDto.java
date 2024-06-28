package br.com.ordertech.customermanagement.external.dto;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.AddressRecord;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Long customerId;
    @NotNull
    private String name;
    private String cpf;
    private String phoneNumber;
    @NotNull
    private AddressDto address;

    public CustomerDto(CustomerEntity customerEntity) {
        this.customerId = customerEntity.getCustomerId();
        this.name = customerEntity.getName();
        this.cpf = customerEntity.getCpf();
        this.phoneNumber = customerEntity.getPhoneNumber();
        this.address =  new AddressDto(customerEntity.getAddress());
        this.address.setAddressId(customerEntity.getAddress().getAddressId());
    }

    public CustomerRecord toRecord() {
        AddressRecord addressRecord = new AddressRecord(address.getStreet(),
                address.getNumber(), address.getComplement(), address.getCity(),
                address.getState(), address.getZipcode(), address.getSubSector());
        return new CustomerRecord(name, cpf, phoneNumber, addressRecord);
    }

}
