package br.com.fiap.delivery.service;

import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeliveryService {

    OrderDeliveryResponse saveDelivery(OrderDelivery orderDelivery);

    ResponseEntity<List<OrderDeliveryResponse>> findDeliveryWhenWiatingDelivery(Integer subSector);

    ResponseEntity<OrderDeliveryResponse> updateSetCourier(String deliveryIDs, String courierID);

    ResponseEntity<List<OrderDeliveryResponse>> updateDeliveryLeft(String courierID);

    ResponseEntity<OrderDeliveryResponse> updateDeliveryCompleted(String deliveryID);

}
