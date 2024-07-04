package br.com.ordertech.order.dto;

import br.com.ordertech.order.enums.StatusPaymentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
}
