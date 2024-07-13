package br.com.ordertech.payment.service;

import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.model.Payment;

public interface PaymentEventGateway {

    void sendPaymentCreatedEvent(PaymentDto payment);

}
