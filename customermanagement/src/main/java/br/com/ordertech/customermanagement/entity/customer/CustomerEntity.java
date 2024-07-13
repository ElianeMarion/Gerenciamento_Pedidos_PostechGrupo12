package br.com.ordertech.customermanagement.entity.customer;

import br.com.ordertech.customermanagement.entity.EntityUtil;

public class CustomerEntity {
    private Long customerId;
    private String name;
    private String cpf;
    private String phoneNumber;
    private Address address;

    public CustomerEntity(String name, String cpf, String phoneNumber) {
        this.name = EntityUtil.isNullOrBlank(name, "Nome é obrigatório");
        this.cpf = EntityUtil.isNullOrBlank(cpf, "cpf é obrigatório");
        this.phoneNumber = EntityUtil.isNullOrBlank(phoneNumber, "Número do telefone é obrigatório");
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }


    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
