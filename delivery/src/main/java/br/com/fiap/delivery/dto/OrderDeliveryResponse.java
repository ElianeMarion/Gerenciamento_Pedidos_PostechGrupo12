package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.model.Courier;
import br.com.fiap.delivery.model.Delivery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class OrderDeliveryResponse extends OrderDelivery {

    @Schema(name = "deliveryID",
            type = "String",
            description = "id da entrega",
            requiredMode = Schema.RequiredMode.AUTO)
    private String deliveryID;

    @Schema(name = "departureDate",
            type = "LocalDateTime",
            description = "data de saída",
            requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime departureDate;

    @Schema(name = "arrivalDate",
            type = "LocalDateTime",
            description = "data de entrega",
            requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime arrivalDate;

    @Schema(name = "courier",
            type = "CourierDto",
            description = "dados do entregador",
            requiredMode = Schema.RequiredMode.AUTO)
    private CourierDto courier;


    public OrderDeliveryResponse(Delivery delivery) {
        super(delivery.getOrder(), delivery.getSenderAddress());
        this.deliveryID = delivery.getDeliveryID();
        this.courier = Optional.ofNullable(delivery.getCourier()).map(Courier::buildDto).orElse(null);
        this.departureDate = delivery.getDepartureDate();
        this.arrivalDate = delivery.getArrivalDate();
    }

    public OrderDeliveryResponse(OrderDto order, AddressDto address) {
        super(order, address);
    }


}
