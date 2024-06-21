package br.com.fiap.delivery.model;

import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Delivery extends OrderDelivery {

    @MongoId
    private String deliveryID;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    @DBRef
    private Courier courier;

    public Delivery(OrderDelivery orderDelivery) {
        super(orderDelivery.getOrder(), orderDelivery.getSenderAddress());
    }

    public OrderDeliveryResponse toOrderDeliveryResponse() {
        OrderDeliveryResponse resp = new OrderDeliveryResponse();
        resp.setDeliveryID(this.deliveryID);
        resp.setOrder(this.getOrder());
        resp.setSenderAddress(this.getSenderAddress());
        resp.setCourier(Optional.ofNullable(this.getCourier()).map(Courier::buildDto).orElse(null));
        resp.setDepartureDate(this.departureDate);
        resp.setArrivalDate(this.arrivalDate);
        return resp;
    }
}
