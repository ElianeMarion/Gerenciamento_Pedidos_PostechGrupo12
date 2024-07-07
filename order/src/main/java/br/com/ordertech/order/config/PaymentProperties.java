package br.com.ordertech.order.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentProperties {
    private String paymentCreatedChannel = "paymentCreatedEventListner-out-0";
    //Producer
    private String paymentUpdatedChannel = "paymentUpdatedEventListner-in-0";
}
