package br.com.ordertech.order.consumer;

import br.com.ordertech.order.dto.OrderDto;
import br.com.ordertech.order.enums.PixStatus;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.model.Pix;
import br.com.ordertech.order.repository.OrderRepository;
import br.com.ordertech.order.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderValidator {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "order-topic", groupId = "grupo")
    public void processaOrder(OrderDto orderDto) {
        Pix pix = pixRepository.findByIdentifier(orderDto.getPix().getIdentifier());
        Order order = orderRepository.findByPixIdentifier(pix.getIdentifier());

        if (pix.getStatus().equals(PixStatus.PROCESSADO)){
            order.setStatusOrder(StatusOrderEnum.APPROVED);
            orderRepository.updateStatusOrder(order.getOrderId(), order.getStatusOrder(), order.getStatus());
        }
    }
}
