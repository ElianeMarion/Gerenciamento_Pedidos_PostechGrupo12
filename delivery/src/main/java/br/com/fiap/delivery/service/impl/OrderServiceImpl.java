package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.OrderDto;
import br.com.fiap.delivery.enums.Status;
import br.com.fiap.delivery.exception.DeliveryException;
import br.com.fiap.delivery.model.Delivery;
import br.com.fiap.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private RestTemplate restTemplate;

    @Value("${URL_MS_ORDER}")
    private String urlMsOrder;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendUpdateOrder(Delivery delivery) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(urlMsOrder)
                .pathSegment(String.valueOf(delivery.getOrder().getOrderID()))
                .pathSegment(String.valueOf(Status.findByDesc(delivery.getOrder().getStatus())));

        ResponseEntity<OrderDto> resp = restTemplate.exchange(uriBuilder.toUriString(),
                HttpMethod.PUT, null, OrderDto.class);

        Optional.of(resp)
                .map(ResponseEntity::getStatusCode)
                .filter(HttpStatusCode::is2xxSuccessful)
                .orElseThrow(() -> new DeliveryException("Não foi possível atualizar o pedido."));
    }

}
