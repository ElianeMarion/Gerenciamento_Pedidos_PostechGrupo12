package br.com.ordertech.customermanagement.entity.customer;

import br.com.ordertech.customermanagement.entity.EntityUtil;

public class CustomerEntity {
    private Integer customerId;
    private String name;
    private Integer cpf;
    private Integer phoneNumber;
    private Address address;

    public CustomerEntity(String name, Integer cpf, Integer phoneNumber) {
        this.name = EntityUtil.isNullOrBlank(name, "Nome é obrigatório");
        this.cpf = EntityUtil.isNull(cpf, "cpf é obrigatório");
        this.phoneNumber = EntityUtil.isNull(phoneNumber, "Número do telefone é obrigatório");
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    /* public void setName(String name) {
        this.name = name;
    }

     */

    public Integer getCpf() {
        return cpf;
    }

    /* public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

     */

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /* public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

     */

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
