package br.com.ordertech.order.repository;


import br.com.ordertech.order.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {

}
