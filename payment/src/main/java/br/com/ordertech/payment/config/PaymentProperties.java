package br.com.ordertech.payment.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentProperties {
    //private String paymentCreatedChannel = "paymentCreatedSupplier-out-0";
    private String paymentCreatedChannel = "paymentCreatedEventListner-out-0";


}
