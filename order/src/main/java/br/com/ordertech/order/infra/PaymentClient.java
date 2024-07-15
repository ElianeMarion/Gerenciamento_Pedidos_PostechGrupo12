package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.PaymentDto;
import br.com.ordertech.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment", url="${URL_PAYMENTS}")
public interface PaymentClient {

    @PostMapping
    void savePayment(@RequestBody PaymentDto paymentDto);
}
