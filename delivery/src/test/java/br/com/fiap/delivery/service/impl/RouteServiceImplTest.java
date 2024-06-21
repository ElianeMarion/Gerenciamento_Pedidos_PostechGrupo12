package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.route.RouteRequest;
import br.com.fiap.delivery.dto.route.RouteResponse;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class RouteServiceImplTest {

    @InjectMocks
    private RouteServiceImpl routeService;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(routeService, "urlGoogleRouteApi", "http://localhost");
        ReflectionTestUtils.setField(routeService, "keyGoogleRouteApi", "http://localhost");
    }

    @Test
    void testFindRoute() {
        RouteRequest req = Util.Objects.buildRouteRequest();
        when(restTemplate.postForObject(Mockito.anyString(), Mockito.any(HttpEntity.class),
                Mockito.eq(RouteResponse.class))).thenReturn(Util.Objects.buildRouteResponse());
        ResponseEntity<RouteResponse> response = routeService.findRoute(req);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRoutes().get(0).getDuration()).isNotNull();
    }

    @Test
    void testFindRouteError() {
        RouteRequest req = Util.Objects.buildRouteRequest();
        when(restTemplate.postForObject(Mockito.anyString(), Mockito.any(HttpEntity.class),
                Mockito.eq(RouteResponse.class))).thenThrow(HttpClientErrorException.class);
        ResponseEntity<RouteResponse> response = routeService.findRoute(req);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
