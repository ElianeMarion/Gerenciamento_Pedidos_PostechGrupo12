package br.com.ordertech.order.service;

import br.com.ordertech.order.exceptions.NotFoundException;
import br.com.ordertech.order.model.OrderLine;
import br.com.ordertech.order.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    public List<OrderLine> getAll(){
        return orderLineRepository.findAll();
    }

    public OrderLine findByOderLineId(Long id){
        return orderLineRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public OrderLine createOrderLine(OrderLine orderLine){

        return orderLineRepository.save(orderLine);
    }

    public OrderLine updateOrderLine(OrderLine orderLine){
        var foundOrderLine = findByOderLineId(orderLine.getOrderLineId());
        foundOrderLine.setPrice(orderLine.getPrice());
        foundOrderLine.setQuantity(orderLine.getQuantity());
        return orderLineRepository.save(foundOrderLine);
    }
}
