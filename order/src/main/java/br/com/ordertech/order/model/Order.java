package br.com.ordertech.order.model;

import br.com.fiap.pedido_cloud.exceptions.InvalidDateException;
import br.com.fiap.pedido_cloud.model.enums.SituacaoPedidoEnum;
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
    private SituacaoPedidoEnum status;
    private Integer deliveryAddressId;
    private Integer originAddressId;
    private List<OrderLine> orderLine;

    @Transient
    private BigDecimal totalOrderValue;


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
