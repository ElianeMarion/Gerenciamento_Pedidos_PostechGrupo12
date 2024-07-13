package br.com.ordertech.order.models;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.exceptions.InvalidDateException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long customerId;
    private LocalDateTime purchaseDate;
    private LocalDateTime deliveryDate;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Long deliveryAddressId;
    private Long originAddressId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLine;

    @Transient
    private BigDecimal totalOrderValue;
    @Transient
    private UUID paymentId;

    @Enumerated(EnumType.STRING)
    private StatusOrderEnum statusOrder;

    public Order(Long orderId, Customer customer, LocalDateTime purchaseDate,
                 LocalDateTime deliveryDate, StatusEnum status, Long deliveryAddressId,
                 Long originAddressId, List<OrderLine> orderLine, BigDecimal totalOrderValue,
                 StatusOrderEnum statusOrder) {
        this.orderId = orderId;
        this.customerId = customer.getCustomerId();
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.deliveryAddressId = deliveryAddressId;
        this.originAddressId = originAddressId;
        this.orderLine = orderLine;
        this.totalOrderValue = totalOrderValue;
        this.statusOrder = statusOrder;

    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        validarData("compra", purchaseDate);
        this.purchaseDate = purchaseDate;
    }

    private void validarData(String tipo, LocalDateTime purchaseDate) {
        LocalDate date = LocalDateTime.now().toLocalDate();
        LocalDate purchase = purchaseDate.toLocalDate();
        if (purchase.isBefore(date)) {
            throw new InvalidDateException("A data da " + tipo + " não pode ser menor que a data atual.");
        }
    }
}
