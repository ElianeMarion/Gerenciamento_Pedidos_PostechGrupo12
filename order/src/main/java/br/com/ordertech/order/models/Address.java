package br.com.ordertech.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long addressID;
    private String street;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
    private Integer subSector;


}
