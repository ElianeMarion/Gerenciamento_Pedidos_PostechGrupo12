package br.com.ordertech.payment.infra;

import br.com.ordertech.payment.config.Mapper;
import br.com.ordertech.payment.config.PaymentProperties;
import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.model.Payment;
import br.com.ordertech.payment.service.PaymentEventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventGatewayWithStream implements PaymentEventGateway {

    private final StreamBridge streamBridge;
    private final PaymentProperties paymentProperties;
    private final Mapper mapper;

    @Override
    public void sendPaymentCreatedEvent(PaymentDto payment) {

        log.info("App criado " + payment.getPaymentId());
  //      PaymentDto map = mapper.map(payment, PaymentDto.class);
        streamBridge.send(paymentProperties.getPaymentCreatedChannel(), payment);

    }
}
