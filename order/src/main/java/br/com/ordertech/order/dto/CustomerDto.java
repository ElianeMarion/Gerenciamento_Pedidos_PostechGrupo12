package br.com.ordertech.order.dto;

import br.com.ordertech.order.models.Address;
import br.com.ordertech.order.models.Customer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    @JsonAlias({"customerId", "customerID"})
    private Long customerID;
    @NotNull
    private String name;
    private String cpf;
    private String phoneNumber;
    @NotNull
    private Address address;

    public CustomerDto(Customer customer) {
        this.customerID = customer.getCustomerId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.phoneNumber = customer.getPhoneNumber();
        this.address =  customer.getAddress();
    }



}
