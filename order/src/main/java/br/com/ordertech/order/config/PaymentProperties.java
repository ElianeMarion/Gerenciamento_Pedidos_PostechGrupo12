package br.com.ordertech.order.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentProperties {
    private String paymentCreatedChannel = "paymentCreatedEventListner-out-0";
    private String paymentOrderCreatedChannel = "order.v1.command.execute-orderPaymentEvent";
}
