package br.com.fiap.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class OrderDelivery {

    @Schema(name = "order",
            type = "OrderDto",
            description = "Dados do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private OrderDto order;


    @Schema(name = "senderAddress",
            type = "AddressDto",
            description = "Endereço para envio do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private AddressDto senderAddress;

}
