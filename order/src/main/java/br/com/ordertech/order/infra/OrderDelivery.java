package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.OrderDeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "delivery", url="${URL_DELIVERY}")
public interface OrderDelivery {

    @PostMapping
    void saveOrderDelivery(@RequestBody OrderDeliveryDto orderDelivery);
}
