package br.com.ordertech.order.repository;


import br.com.ordertech.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {




}
