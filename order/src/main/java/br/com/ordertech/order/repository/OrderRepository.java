package br.com.ordertech.order.repository;


import br.com.ordertech.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {

}
