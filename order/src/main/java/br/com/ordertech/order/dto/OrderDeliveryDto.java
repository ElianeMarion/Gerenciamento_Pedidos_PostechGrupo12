package br.com.ordertech.order.dto;

import br.com.ordertech.order.models.Address;
import br.com.ordertech.order.models.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryDto {

    @NotNull
    @Valid
    private Order order;

    @NotNull
    @Valid
    private Address senderAddress;

}
