package br.com.ordertech.payment.service;

import br.com.ordertech.payment.dto.PaymentDto;

public interface OrderPaymentEvent {
    void sendOrderToPayment(PaymentDto paymentDto);
}
