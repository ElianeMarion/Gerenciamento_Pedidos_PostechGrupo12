package br.com.ordertech.order.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @JsonAlias({"addressId", "addressID"})
    private Long addressID;
    private String street;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
    private Integer subSector;
}
