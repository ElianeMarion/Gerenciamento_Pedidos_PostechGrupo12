package br.com.ordertech.customermanagement.external.model;


import br.com.ordertech.customermanagement.entity.customer.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;
    private Integer subSector;

    public AddressModel(Address address, Long customerId) {
        BeanUtils.copyProperties(address, this);
        this.addressId = customerId;
    }

    public Address toAddress() {
        return new Address(this.street, this.number, this.complement,
                this.city, this.state, this.zipcode);
    }
}
