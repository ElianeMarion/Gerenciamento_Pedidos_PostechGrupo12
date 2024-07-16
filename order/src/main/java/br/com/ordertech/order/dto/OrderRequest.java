package br.com.ordertech.order.dto;

import br.com.ordertech.order.enums.StatusEnum;

import br.com.ordertech.order.models.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull
    @Valid
    private Long orderID;

    private CustomerDto customer;
    @NotNull
    private LocalDateTime purchaseDate;

    private LocalDateTime deliveryDate;

    @NotNull
    private StatusEnum status;

    @NotNull
    @Valid
    private Address sendAddress;
    @NotEmpty
    @Valid
    private List<OrderLineDto> orderLines;

}
