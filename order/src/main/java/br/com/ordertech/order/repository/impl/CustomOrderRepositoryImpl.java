package br.com.ordertech.order.repository.impl;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.repository.CustomOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {


    private final MongoTemplate mongoTemplate;

    public CustomOrderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateStatusOrder(Long orderId, StatusOrderEnum statusOrder, StatusEnum status) {
        Query query = new Query(Criteria.where("orderId").is(orderId));
        Update update = new Update()
                .set("statusOrder", statusOrder)
                .set("status", status);
        mongoTemplate.updateFirst(query, update, Order.class);
    }

    @Override
    public Order findByPixIdentifier(String identifier) {
        Query query = new Query(Criteria.where("pix.identifier").is(identifier));
        return mongoTemplate.findOne(query, Order.class);
    }
}
