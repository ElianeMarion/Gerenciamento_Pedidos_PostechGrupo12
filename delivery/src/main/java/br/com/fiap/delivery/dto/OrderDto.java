package br.com.fiap.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    @Schema(name = "orderID",
            type = "Long",
            description = "id do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private Long orderID;

    @Schema(name = "customer",
            type = "CustomerDto",
            description = "dados do cliente",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private CustomerDto customer;

    @Schema(name = "purchaseDate",
            type = "LocalDateTime",
            description = "data da compra",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private LocalDateTime purchaseDate;

    @Schema(name = "deliveryDate",
            type = "LocalDateTime",
            description = "data da entrega",
            requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime deliveryDate;

    @Schema(name = "status",
            type = "String",
            description = "status do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private String status;

    @Schema(name = "oderLines",
            type = "List",
            description = "itens do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotEmpty
    @Valid
    private List<OrderLineDto> orderLines;

}
