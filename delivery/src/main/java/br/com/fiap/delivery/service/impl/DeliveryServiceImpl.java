package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import br.com.fiap.delivery.enums.Status;
import br.com.fiap.delivery.exception.DeliveryException;
import br.com.fiap.delivery.model.Courier;
import br.com.fiap.delivery.model.Delivery;
import br.com.fiap.delivery.repository.DeliveryRepository;
import br.com.fiap.delivery.service.CourierService;
import br.com.fiap.delivery.service.DeliveryService;
import br.com.fiap.delivery.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    private MongoTemplate mongoTemplate;

    private CourierService courierService;

    private OrderService orderService;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository,
                               MongoTemplate mongoTemplate,
                               CourierService courierService,
                               OrderService orderService) {
        this.deliveryRepository = deliveryRepository;
        this.mongoTemplate = mongoTemplate;
        this.courierService = courierService;
        this.orderService = orderService;
    }

    @Override
    public OrderDeliveryResponse saveDelivery(OrderDelivery orderDelivery) {
        Delivery delivery = new Delivery(orderDelivery);
        delivery = deliveryRepository.save(delivery);
        return new OrderDeliveryResponse(delivery);
    }

    @Override
    public ResponseEntity<List<OrderDeliveryResponse>> findDeliveryWhenWiatingDelivery(Integer subSector) {
        Criteria criteria = Criteria
                .where("senderAddress.subSector").is(subSector)
                .and("order.status").regex(Status.WAITING_DELIVERY.getDesc(), "i");
        Query query = new Query(criteria);
        try {
            List<Delivery> resp = mongoTemplate.find(query, Delivery.class);
            return Optional.ofNullable(resp)
                    .map(list -> list.stream().map(OrderDeliveryResponse::new).toList())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<OrderDeliveryResponse> updateSetCourier(String deliveryID, String courierID) {
        Courier courier = courierService.findById(courierID);
        Criteria criteria = Criteria.where("deliveryID").is(deliveryID)
                .and("courier").exists(false)
                .and("order.status").is(Status.WAITING_DELIVERY.getDesc());
        Query query = new Query(criteria);
        List<Delivery> deliveryLocate = mongoTemplate.find(query, Delivery.class);
        Delivery delivery = Optional.of(deliveryLocate)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .orElseThrow(() -> new DeliveryException("Entrega não localizada."));
        delivery.setCourier(courier);
        deliveryRepository.save(delivery);
        return ResponseEntity.ok(delivery.toOrderDeliveryResponse());
    }

    @Override
    public ResponseEntity<List<OrderDeliveryResponse>> updateDeliveryLeft(String courierID) {
        Criteria criteria = Criteria.where("courier.courierID").is(courierID)
                .and("order.status").is(Status.WAITING_DELIVERY.getDesc());
        Query query = new Query(criteria);
        List<Delivery> delivery = Optional.of(mongoTemplate.find(query, Delivery.class))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DeliveryException("Entregas não localizadas"));
        delivery.forEach(item -> {
            item.getOrder().setStatus(Status.IN_TRANSIT.getDesc());
            item.setDepartureDate(LocalDateTime.now());
            deliveryRepository.save(item);
            sendUpdateOrder(item);
        });
        List<OrderDeliveryResponse> resp = delivery.stream()
                .filter(item -> item.getOrder().getStatus().equals(Status.IN_TRANSIT.getDesc()))
                .map(Delivery::toOrderDeliveryResponse)
                .toList();
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<OrderDeliveryResponse> updateDeliveryCompleted(String deliveryID) {
        Criteria criteria = Criteria.where("deliveryID").is(deliveryID)
                .and("order.status").is(Status.IN_TRANSIT.getDesc());
        Query query = new Query(criteria);
        List<Delivery> deliveries = mongoTemplate.find(query, Delivery.class);
        Delivery deliveryLocate = Optional.of(deliveries)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .map(delivery -> {
                    delivery.getOrder().setStatus(Status.DELIVERY_COMPLETED.getDesc());
                    delivery.setArrivalDate(LocalDateTime.now());
                    return delivery;
                }).orElseThrow(() -> new DeliveryException("Entrega não localizada."));
        deliveryRepository.save(deliveryLocate);
        Optional.ofNullable(deliveryLocate.getCourier()).ifPresent(c -> {
            Courier courier = courierService.findById(deliveryLocate.getCourier().getCourierID());
            courier.setLastDelivery(LocalDateTime.now());
            courierService.saveCourier(courier.buildDto());
        });
        sendUpdateOrder(deliveryLocate);
        return ResponseEntity.ok(deliveryLocate.toOrderDeliveryResponse());
    }

    protected void sendUpdateOrder(Delivery delivery) {
        try {
            orderService.sendUpdateOrder(delivery);
        } catch(Exception e) {
            delivery.getOrder().setStatus(Status.WAITING_DELIVERY.getDesc());
            deliveryRepository.save(delivery);
        }
    }

}
