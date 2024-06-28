package br.com.ordertech.order.repository;


import br.com.ordertech.order.model.OrderLine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderLineRepository extends MongoRepository<OrderLine, Long> {
}
