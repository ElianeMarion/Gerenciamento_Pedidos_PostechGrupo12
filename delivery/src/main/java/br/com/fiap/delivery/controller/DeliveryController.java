package br.com.fiap.delivery.controller;

import br.com.fiap.delivery.controller.swagger.DeliveryControllerSwagger;
import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import br.com.fiap.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/delivery")
public class DeliveryController implements DeliveryControllerSwagger {

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<OrderDeliveryResponse> saveDelivery(
            @RequestBody @Valid OrderDelivery orderDelivery) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deliveryService.saveDelivery(orderDelivery));
    }

    @GetMapping
    public ResponseEntity<List<OrderDeliveryResponse>> findDeliveryWhenWaitingDelivery(@RequestParam Integer subSector) {
        return deliveryService.findDeliveryWhenWiatingDelivery(subSector);
    }

    @PutMapping
    public ResponseEntity<OrderDeliveryResponse> updateSetCourier(@RequestParam String deliveryID,
                                                                  @RequestParam String courierID) {
        return deliveryService.updateSetCourier(deliveryID, courierID);
    }

    @PutMapping("/left/{courierID}")
    public ResponseEntity<List<OrderDeliveryResponse>> updateDeliveryLeft(@PathVariable String courierID) {
        return deliveryService.updateDeliveryLeft(courierID);
    }

    @PutMapping("/completed/{deliveryID}")
    public ResponseEntity<OrderDeliveryResponse> updateDeliveryCompleted(@PathVariable String deliveryID) {
        return deliveryService.updateDeliveryCompleted(deliveryID);
    }

}
