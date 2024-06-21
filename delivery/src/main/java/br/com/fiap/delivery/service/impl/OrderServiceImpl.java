package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.model.Delivery;
import br.com.fiap.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    private RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendUpdateOrder(Delivery delivery) {
        //TODO
    }

}
