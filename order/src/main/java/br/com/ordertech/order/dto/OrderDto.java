package br.com.ordertech.order.dto;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.model.Pix;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    @Schema(name = "orderId",
        type = "Long",
    description = "id do pedido",
    requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private Long orderId;

    @Schema(name = "customer",
            type = "CustomerDto",
            description = "dados do cliente",
            requiredMode = Schema.RequiredMode.AUTO)
    private CustomerDto customer;


    private Integer customerId;

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
            description = "status de entrega do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    private StatusEnum status;

    @Schema(name = "deliveryAddressId",
            type = "AddressDto",
            description = "Endereço para envio do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private AddressDto deliveryAddressId;

    @Schema(name = "originAddressId",
            type = "AddressDto",
            description = "Endereço para envio do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotNull
    @Valid
    private AddressDto originAddressId;

    @Schema(name = "oderLines",
            type = "List",
            description = "itens do pedido",
            requiredMode = Schema.RequiredMode.AUTO)
    @NotEmpty
    @Valid
    private List<OrderLineDto> orderLines;
    private BigDecimal totalOrderValue;
    private StatusOrderEnum statusOrder;
    private Pix pix;

}
