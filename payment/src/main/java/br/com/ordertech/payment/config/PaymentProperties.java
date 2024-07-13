package br.com.ordertech.payment.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentProperties {
    private String paymentCreatedChannel = "paymentCreatedEventListner-out-0";
    private String paymentUpdatedChannel = "paymentUpdatedEventListner";


}
