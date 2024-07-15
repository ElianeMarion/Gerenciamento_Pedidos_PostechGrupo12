package br.com.ordertech.order.repository;


import br.com.ordertech.order.models.Order;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderLine WHERE o.orderId = :id")
    Optional<Order> findByIdWithOrderLines(@Param("id") Long id);
}
