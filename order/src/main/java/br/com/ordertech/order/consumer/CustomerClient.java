package br.com.ordertech.order.consumer;

import br.com.ordertech.order.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customermanagement", url = "${customermanagement.url}",
        fallback = CustomerClientFallback.class)
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerDto getCustomerById(@PathVariable("id") Integer id);
}
