package br.com.ordertech.customermanagement.infraestructure.presenter.customer;

public record CustomerRecord(String name, String cpf, String phoneNumber, AddressRecord address) {

}
