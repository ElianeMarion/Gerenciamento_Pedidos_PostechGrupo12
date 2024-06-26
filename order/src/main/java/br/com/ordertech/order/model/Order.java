package br.com.ordertech.order.model;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StautsOrderEnum;
import br.com.ordertech.order.exceptions.InvalidDateException;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("order")
@Jacksonized
public class Order {
    @Id
    private Long orderId;
    private Integer customerId;
    private LocalDateTime purchaseDate;
    private LocalDateTime deliveryDate;
    private StatusEnum status;
    private Integer deliveryAddressId;
    private Integer originAddressId;
    private List<OrderLine> orderLine;

    @Transient
    private BigDecimal totalOrderValue;

    private StautsOrderEnum statusOrder;


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", deliveryDate=" + deliveryDate +
                ", status=" + status +
                ", deliveryAdressId=" + deliveryAddressId +
                ", originAdressId=" + originAddressId +
                ", orderLine=" + orderLine +
                ", statusOrder = " + statusOrder +
                '}';
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        validarData("compra", purchaseDate);
        this.purchaseDate = purchaseDate;

    }

    private void validarData(String tipo, LocalDateTime purchaseDate) {
        if (purchaseDate.isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("A data da " + tipo + " não pode ser menor que a data atual.");
        }
    }
}
