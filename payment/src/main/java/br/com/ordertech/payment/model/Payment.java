package br.com.ordertech.payment.model;

import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.enums.StatusPaymentEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "payment")
@Data
@Builder
public class Payment {
    @Id
    private UUID paymentId;
    private Long orderId;
    private LocalDateTime datePayment;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private StatusPaymentEnum status;


    public Payment(){
      //  this.paymentId = UUID.randomUUID();
        this.datePayment = LocalDateTime.now();
    }

    public Payment(Long orderId, BigDecimal value, StatusPaymentEnum status) {
      //  this.paymentId = UUID.randomUUID();
        this.datePayment = LocalDateTime.now();
        this.orderId = orderId;
        this.value = value;
        this.status = status;
    }

    public Payment(UUID paymentId, Long orderId, BigDecimal value, StatusPaymentEnum status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.value = value;
        this.status = status;
    }

    public Payment(UUID paymentId, Long orderId, LocalDateTime datePayment,
                   BigDecimal value, StatusPaymentEnum status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.datePayment = datePayment;
        this.value = value;
        this.status = status;
    }
}
