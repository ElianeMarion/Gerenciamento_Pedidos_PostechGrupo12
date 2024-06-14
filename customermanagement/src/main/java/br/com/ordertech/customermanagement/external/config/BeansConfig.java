package br.com.ordertech.customermanagement.external.config;

import br.com.ordertech.customermanagement.infraestructure.controller.CustomerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public CustomerController customerController() {
        return new CustomerController();
    }
}
