package br.com.ordertech.order.dtos;

import br.com.ordertech.order.dto.PaymentDto;
import br.com.ordertech.order.enums.StatusPaymentEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
public class PaymentDtoTest {

    @Test
    public void testNoArgsConstructor() {
        PaymentDto paymentDto = new PaymentDto();

        assertThat(paymentDto.getPaymentId()).isNotNull();
        assertThat(paymentDto.getOrderId()).isNull();
        assertThat(paymentDto.getValue()).isNull();
        assertThat(paymentDto.getStatus()).isNull();
    }

    @Test
    public void testAllArgsConstructor() {
        UUID paymentId = UUID.randomUUID();
        Long orderId = 1L;
        BigDecimal value = BigDecimal.valueOf(100.00);
        StatusPaymentEnum status = StatusPaymentEnum.PROCESSING;

        PaymentDto paymentDto = new PaymentDto(paymentId, orderId, value, status);

        assertThat(paymentDto.getPaymentId()).isEqualTo(paymentId);
        assertThat(paymentDto.getOrderId()).isEqualTo(orderId);
        assertThat(paymentDto.getValue()).isEqualByComparingTo(value);
        assertThat(paymentDto.getStatus()).isEqualTo(status);
    }

    @Test
    public void testBuilder() {
        UUID paymentId = UUID.randomUUID();
        Long orderId = 1L;
        BigDecimal value = BigDecimal.valueOf(100.00);
        StatusPaymentEnum status = StatusPaymentEnum.PROCESSING;

        PaymentDto paymentDto = PaymentDto.builder()
                .paymentId(paymentId)
                .orderId(orderId)
                .value(value)
                .status(status)
                .build();

        assertThat(paymentDto.getPaymentId()).isEqualTo(paymentId);
        assertThat(paymentDto.getOrderId()).isEqualTo(orderId);
        assertThat(paymentDto.getValue()).isEqualByComparingTo(value);
        assertThat(paymentDto.getStatus()).isEqualTo(status);
    }
}
