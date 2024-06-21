package br.com.fiap.delivery.service;

import br.com.fiap.delivery.dto.route.RouteRequest;
import br.com.fiap.delivery.dto.route.RouteResponse;
import org.springframework.http.ResponseEntity;

public interface RouteService {

    ResponseEntity<RouteResponse> findRoute(RouteRequest routeRequest);

}
