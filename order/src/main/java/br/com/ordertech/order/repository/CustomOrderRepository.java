package br.com.ordertech.order.repository;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.model.Order;

public interface CustomOrderRepository {
    void updateStatusOrder(Long orderId, StatusOrderEnum statusOrderEnum, StatusEnum status);

    Order findByPixIdentifier(String identifier);
}
