package br.com.ordertech.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private Integer idAddress;
    private String street;
    private int number;
    private String city;
    private String state;
    private int subSector;
    private int zipCode;

    public AddressRequest(Integer idAddress, String street, int number, String city, String state, int subSector, int zipCode) {
        this.idAddress = idAddress;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.subSector = subSector;
        this.zipCode = zipCode;
    }


}
