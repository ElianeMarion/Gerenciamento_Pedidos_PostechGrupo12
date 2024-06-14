package br.com.ordertech.customermanagement.external.dto;


import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.AddressRecord;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull
    private String street;
    @NotNull
    private Integer number;
    private String complement;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private Integer zipcode;
    @NotNull
    private Integer subSector;

    public AddressRecord toRecord() {
        return new AddressRecord(street, number, complement, city, state, zipcode, subSector);
    }

    public AddressDto(Address address) {
        BeanUtils.copyProperties(address, this);
    }

}
