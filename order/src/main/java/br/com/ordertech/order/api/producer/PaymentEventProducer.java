package br.com.ordertech.order.api.producer;

import br.com.ordertech.order.config.PaymentProperties;
import br.com.ordertech.order.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer implements PaymentEvent{

    private final StreamBridge streamBridge;
    private final PaymentProperties paymentProperties;

    @Override
    public void sendPaymentUpdatedEvent(PaymentDto paymentDto) {
        log.info("Pagamento alterado " + paymentDto.getPaymentId());
        streamBridge.send(paymentProperties.getPaymentUpdatedChannel(), paymentDto);
    }
}
