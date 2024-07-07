package br.com.ordertech.order.model;

import br.com.ordertech.order.enums.StatusPaymentEnum;
import br.com.ordertech.order.models.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    @Test
    public void testNoArgsConstructor() {
        Payment payment = new Payment();

        assertThat(payment.getPaymentId()).isNotNull();
        assertThat(payment.getDatePayment()).isNotNull();
        assertThat(payment.getOrderId()).isNull();
        assertThat(payment.getValue()).isNull();
        assertThat(payment.getStatus()).isNull();
    }

    @Test
    public void testConstructorWithOrderValueAndStatus() {
        Long orderId = 1L;
        BigDecimal value = BigDecimal.valueOf(100.00);
        StatusPaymentEnum status = StatusPaymentEnum.PROCESSING;

        Payment payment = new Payment(orderId, value, status);

        assertThat(payment.getPaymentId()).isNotNull();
        assertThat(payment.getDatePayment()).isNotNull();
        assertThat(payment.getOrderId()).isEqualTo(orderId);
        assertThat(payment.getValue()).isEqualByComparingTo(value);
        assertThat(payment.getStatus()).isEqualTo(status);
    }

    @Test
    public void testAllArgsConstructor() {
        UUID paymentId = UUID.randomUUID();
        Long orderId = 1L;
        LocalDateTime datePayment = LocalDateTime.now();
        BigDecimal value = BigDecimal.valueOf(100.00);
        StatusPaymentEnum status = StatusPaymentEnum.PROCESSING;

        Payment payment = new Payment(paymentId, orderId, datePayment, value, status);

        assertThat(payment.getPaymentId()).isEqualTo(paymentId);
        assertThat(payment.getDatePayment()).isEqualTo(datePayment);
        assertThat(payment.getOrderId()).isEqualTo(orderId);
        assertThat(payment.getValue()).isEqualByComparingTo(value);
        assertThat(payment.getStatus()).isEqualTo(status);
    }

    @Test
    public void testBuilder() {
        UUID paymentId = UUID.randomUUID();
        Long orderId = 1L;
        LocalDateTime datePayment = LocalDateTime.now();
        BigDecimal value = BigDecimal.valueOf(100.00);
        StatusPaymentEnum status = StatusPaymentEnum.PROCESSING;

        Payment payment = Payment.builder()
                .paymentId(paymentId)
                .orderId(orderId)
                .datePayment(datePayment)
                .value(value)
                .status(status)
                .build();

        assertThat(payment.getPaymentId()).isEqualTo(paymentId);
        assertThat(payment.getDatePayment()).isEqualTo(datePayment);
        assertThat(payment.getOrderId()).isEqualTo(orderId);
        assertThat(payment.getValue()).isEqualByComparingTo(value);
        assertThat(payment.getStatus()).isEqualTo(status);
    }
}
