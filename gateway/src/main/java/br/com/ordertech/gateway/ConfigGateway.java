package br.com.ordertech.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {

    @Value("${URL_BASE}")
    private String urlBase;

    @Bean
    public RouteLocator custom(RouteLocatorBuilder builder) {
         return builder.routes()
                .route("customer", r -> r.path("/customer/**")
                        .and().not(p -> p.path("/customer/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri(String.format("http://%s:8081", urlBase)))
                .route("product", r -> r.path("/product/**")
                        .and().not(p -> p.path("/product/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri(String.format("http://%s:8082", urlBase)))
                .route("order", r -> r.path("/order/**")
                        .and().not(p -> p.path("/order/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri(String.format("http://%s:8083", urlBase)))
                .route("delivery", r -> r.path("/delivery/**")
                        .and().not(p -> p.path("/delivery/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri(String.format("http://%s:8084", urlBase)))
                .route("load", r -> r.path("/load/**")
                        .and().not(p -> p.path("/load/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri(String.format("http://%s:8085", urlBase)))
                 .build();
    }
}
