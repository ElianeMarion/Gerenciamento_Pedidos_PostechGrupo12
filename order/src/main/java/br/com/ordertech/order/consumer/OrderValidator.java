package br.com.ordertech.order.consumer;

import br.com.ordertech.order.dto.OrderDto;
import br.com.ordertech.order.enums.PixStatus;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.model.Pix;
import br.com.ordertech.order.repository.OrderRepository;
import br.com.ordertech.order.repository.PixRepository;
import br.com.ordertech.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderValidator {

    private final OrderService orderService;

    public OrderValidator(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "order-topic", groupId = "grupo")
    //topics = "${order-topic}", groupId = "${order-topic.group-id}")
    public void processaOrder(OrderDto orderDto) {

        if (orderDto.getPix().getStatus().equals(PixStatus.PROCESSADO)){
            orderDto.setStatusOrder(StatusOrderEnum.APPROVED);
            orderService.updateStatus(orderDto.getOrderId(), orderDto.getStatusOrder());
        }
    }
}
