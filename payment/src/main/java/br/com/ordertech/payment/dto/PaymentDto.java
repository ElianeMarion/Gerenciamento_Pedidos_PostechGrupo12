package br.com.ordertech.payment.dto;

import br.com.ordertech.payment.enums.StatusPaymentEnum;
import br.com.ordertech.payment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private UUID paymentId;
    private Long orderId;
    private BigDecimal value;
    private StatusPaymentEnum status;

    public Payment toPayment(PaymentDto paymentDto){
        Payment payment = new Payment(paymentDto.getPaymentId(),
                paymentDto.getOrderId(),
                paymentDto.getValue(),
                paymentDto.getStatus());
        return payment;
    }
}
