package br.com.ordertech.customermanagement.infraestructure.presenter.customer;

public record AddressRecord(
        String street,
        Integer number,
        String complement,
        String city,
        String state,
        String zipcode,
        Integer subSector
) {
}
