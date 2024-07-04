package br.com.ordertech.order.service;

import br.com.ordertech.order.dto.PaymentDto;

public interface OrderPaymentEvent {
    void sendOrderToPayment(PaymentDto paymentDto);
}
