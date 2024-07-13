package br.com.fiap.delivery.controller;

import br.com.fiap.delivery.controller.swagger.RouteControllerSwagger;
import br.com.fiap.delivery.dto.route.RouteRequest;
import br.com.fiap.delivery.dto.route.RouteResponse;
import br.com.fiap.delivery.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery/route")
public class RouteController implements RouteControllerSwagger {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @GetMapping
    public ResponseEntity<RouteResponse> findRoute(
            @RequestParam String addressOrigin,
            @RequestParam String addressDestination,
            @RequestParam(required = false, defaultValue = "false") boolean bestRouteInRealTime,
            @RequestParam(required = false, defaultValue = "drive") String typeOfTransport,
            @RequestParam(required = false, defaultValue = "false") boolean avoidHighways,
            @RequestParam(required = false, defaultValue = "false") boolean avoidTols) {
        RouteRequest req = new RouteRequest(addressOrigin, addressDestination,
                bestRouteInRealTime, typeOfTransport, avoidHighways, avoidTols);

        return routeService.findRoute(req);
    }


}
