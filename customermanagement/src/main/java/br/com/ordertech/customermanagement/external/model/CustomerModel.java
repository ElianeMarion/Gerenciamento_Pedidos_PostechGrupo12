package br.com.ordertech.customermanagement.external.model;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntityBuild;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_customer")
@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer customerId;
    private String name;
    private Integer cpf;
    private Integer phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_addressId")
    private AddressModel addressModel;

/*    public CustomerModel(Integer customerId) {
        this.customerId = customerId;
    }

 */

    public CustomerModel(CustomerEntity customerEntity) {
        this.name = customerEntity.getName();
        this.cpf = customerEntity.getCpf();
        this.phoneNumber = customerEntity.getPhoneNumber();
        Address address = customerEntity.getAddress();
        this.addressModel = new AddressModel(address, customerId);
    }

    public CustomerEntity toEntity() {
        Address address = new Address(addressModel.getStreet(), addressModel.getNumber(),
                addressModel.getComplement(), addressModel.getCity(), addressModel.getState(),
                addressModel.getZipcode());
        CustomerEntity customerEntity = new CustomerEntityBuild()
                .addInfos(this.name, this.cpf, this.phoneNumber)
                .addAddress(address)
                .build();
        customerEntity.setCustomerId(customerId);
        return customerEntity;
    }

}
