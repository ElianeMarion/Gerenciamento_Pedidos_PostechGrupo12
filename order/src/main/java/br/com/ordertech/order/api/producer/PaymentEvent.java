package br.com.ordertech.order.api.producer;

import br.com.ordertech.order.dto.PaymentDto;

public interface PaymentEvent {
    void sendPaymentUpdatedEvent(PaymentDto paymentDto);
}
