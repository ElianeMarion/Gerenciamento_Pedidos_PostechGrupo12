package br.com.ordertech.customermanagement.infraestructure.presenter.customer;

public record CustomerRecord(String name, Integer cpf, Integer phoneNumber, AddressRecord address) {

}
