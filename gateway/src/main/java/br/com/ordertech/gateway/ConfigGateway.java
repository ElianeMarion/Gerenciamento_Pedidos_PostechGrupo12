package br.com.ordertech.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {
    @Bean
    public RouteLocator custom(RouteLocatorBuilder builder) {
         return builder.routes()
                .route("customer", r -> r.path("/customer/**")
                        .and().not(p -> p.path("/customer/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081"))
                .route("product", r -> r.path("/product/**")
                        .and().not(p -> p.path("/product/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082"))
                .route("order", r -> r.path("/order/**")
                        .and().not(p -> p.path("/order/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8083"))
                .route("delivery", r -> r.path("/delivery/**")
                        .and().not(p -> p.path("/delivery/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8084"))
                .route("load", r -> r.path("/load/**")
                        .and().not(p -> p.path("/load/api/**"))
//                      .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8085"))
                 .build();
    }
}
