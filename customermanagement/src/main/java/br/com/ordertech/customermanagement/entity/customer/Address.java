package br.com.ordertech.customermanagement.entity.customer;

import br.com.ordertech.customermanagement.entity.EntityUtil;

public class Address {
    private Long addressId;
    private String street;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;
    private Integer subSector;

    public Address(String street, Integer number, String complement, String city, String state, String zipcode) {
        this.street = EntityUtil.isNullOrBlank(street, "Nome da rua é obrigatório");
        this.number = EntityUtil.isNull(number, "Número é obrigatório");
        this.complement = complement;
        this.city = EntityUtil.isNullOrBlank(city, "Cidade é obrigatória");
        this.state = EntityUtil.isNullOrBlank(state, "Estado é obrigatório");
        this.zipcode = EntityUtil.isNullOrBlank(zipcode, "CEP é obrigatório");
        String s = zipcode.substring(2, 3);
        this.subSector = Integer.valueOf(s);
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Integer getSubSector() {
        return subSector;
    }

}
