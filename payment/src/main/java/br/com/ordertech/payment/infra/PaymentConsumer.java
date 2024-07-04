package br.com.ordertech.payment.infra;

import br.com.ordertech.payment.config.Mapper;
import br.com.ordertech.payment.config.PaymentProperties;
import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.model.Payment;
import br.com.ordertech.payment.service.OrderPaymentEvent;
import br.com.ordertech.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer implements Consumer<PaymentDto> {

    private final StreamBridge streamBridge;
    private final Mapper mapper;
    private final PaymentService paymentService;
    private final PaymentProperties paymentProperties;

    @Override
    public void accept(PaymentDto paymentDto) {

        log.info("Payment criado recebido" + paymentDto.getPaymentId());
        /*Payment payment = Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .value(paymentDto.getValue())
                .orderId(paymentDto.getOrderId())
                .status(paymentDto.getStatus())
                .build();*/

        paymentService.savePayment(paymentDto);
        streamBridge.send(paymentProperties.getPaymentCreatedChannel(), paymentDto);

    }
}
