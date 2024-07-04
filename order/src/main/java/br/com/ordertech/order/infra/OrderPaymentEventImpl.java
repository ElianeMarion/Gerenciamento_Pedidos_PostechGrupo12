package br.com.ordertech.order.infra;

import br.com.ordertech.order.api.Mapper;
import br.com.ordertech.order.config.PaymentProperties;
import br.com.ordertech.order.dto.PaymentDto;
import br.com.ordertech.order.service.OrderPaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderPaymentEventImpl implements OrderPaymentEvent {

    private final StreamBridge streamBridge;
    private final PaymentProperties paymentProperties;
    private final Mapper mapper;
    @Override
    public void sendOrderToPayment(PaymentDto paymentDto) {
        streamBridge.send(paymentProperties.getPaymentCreatedChannel(), paymentDto);
        log.info("Pagamento enviado " + paymentDto.getOrderId());

    }
}
