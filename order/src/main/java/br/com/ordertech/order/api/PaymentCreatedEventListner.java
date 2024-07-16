package br.com.ordertech.order.api;

import br.com.ordertech.order.dto.PaymentDto;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusPaymentEnum;
import br.com.ordertech.order.models.Payment;
import br.com.ordertech.order.repository.impl.PaymentRepository;
import br.com.ordertech.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCreatedEventListner implements Consumer<PaymentDto> {

    private final OrderService orderService;

    @Override
    public void accept(PaymentDto paymentDto) {
        log.info("Payment criado recebido " + paymentDto.getPaymentId() + paymentDto.getStatus());
        if(paymentDto.getStatus() == null)
            paymentDto.setStatus(StatusPaymentEnum.PROCESSING);
        Payment payment = Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .value(paymentDto.getValue())
                .orderId(paymentDto.getOrderId())
                .status(paymentDto.getStatus())
                .build();
        if(payment.getStatus().equals(StatusPaymentEnum.PAYMENT_COMPLETED)){
            orderService.updateStatusByStatusName(payment.getOrderId(), StatusEnum.WAITING_DELIVERY);
            log.info("Pedido pago " + payment);
        } else if (payment.getStatus().equals(StatusPaymentEnum.PAYMENT_ERROR)) {
            orderService.canceledOrderReturnStock(payment.getOrderId());
            log.info("Pedido cancelado por pagamento cancelado" + payment);
        }


    }

}
