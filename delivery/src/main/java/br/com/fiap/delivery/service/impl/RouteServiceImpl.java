package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.route.RouteRequest;
import br.com.fiap.delivery.dto.route.RouteResponse;
import br.com.fiap.delivery.service.RouteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Log4j2
@Service
public class RouteServiceImpl implements RouteService {

    @Qualifier("ssl")
    private final RestTemplate restTemplate;

    @Autowired
    public RouteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${URL_GOOGLE_ROUTE_API:http://}")
    private String urlGoogleRouteApi;

    @Value("${KEY_GOOGLE_ROUTE_API:443}")
    private String keyGoogleRouteApi;

    @Override
    public ResponseEntity<RouteResponse> findRoute(RouteRequest routeRequest) {
        HttpHeaders headers = buildHeaders();
        HttpEntity<Object> entity = new HttpEntity(routeRequest, headers);
        try {
            log.error("Buscando rota... {}", routeRequest);
            RouteResponse routeResponse = restTemplate.postForObject(urlGoogleRouteApi, entity, RouteResponse.class);
            log.error("Rota retornada {}", routeResponse);
            return Optional.ofNullable(routeResponse).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch(Exception e) {
            log.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Goog-FieldMask","*");
        headers.add("X-Goog-Api-Key",keyGoogleRouteApi);
        return headers;
    }
}
