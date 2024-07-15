package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customermanagement", url = "${URL_CUSTOMER}",//"${customermanagement.url}",
        fallback = CustomerClientFallback.class)
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerDto getCustomerById(@PathVariable("id") Long id);
}
