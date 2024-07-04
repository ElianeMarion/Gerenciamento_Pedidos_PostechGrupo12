package br.com.ordertech.order.repository.impl;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.models.Order;
import br.com.ordertech.order.repository.CustomOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomOrderRepositoryImpl implements CustomOrderRepository {


    private final EntityManager entityManager;

    @Autowired
    public CustomOrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updateStatusOrder(Long orderId, StatusOrderEnum statusOrder, StatusEnum status) {
        Query query = entityManager.createQuery("UPDATE Order o SET o.statusOrder = :statusOrder, o.status = :status WHERE o.orderId = :orderId");
        query.setParameter("statusOrder", statusOrder);
        query.setParameter("status", status);
        query.setParameter("orderId", orderId);
        query.executeUpdate();
    }

    @Override
    public Order findByPixIdentifier(String identifier) {
    /*    Query query = entityManager.createQuery("SELECT o FROM Order o WHERE o.pix.identifier = :identifier", Order.class);
        query.setParameter("identifier", identifier);
        return (Order) query.getSingleResult();*/
        return null;
    }

}
