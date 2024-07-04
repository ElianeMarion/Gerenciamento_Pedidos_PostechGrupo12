package br.com.ordertech.order.models;

import br.com.ordertech.order.exceptions.InvalidValueException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;
    private Long productId;
    private int quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderLine(Long orderLineId, Long productId, int quantity) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        setQuantity(quantity);
    }

    public void setQuantity(int quantity) {
        validQuantity(quantity);
        this.quantity = quantity;
    }

    private void validQuantity(int quantity) {
        if (quantity < 0 )
            throw new InvalidValueException("Quantidade inválida, o valor informado não pode ser negativo.");
    }

    public void setPrice(BigDecimal price) {
        validPrice(price);
        this.price = price;
    }

    private void validPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0 )
            throw new InvalidValueException("Preço inválido, o valor informado não pode ser negativo.");
        else if (price.compareTo(BigDecimal.ZERO) == 0 )
            throw new InvalidValueException("O preço do produto deve ser informado.");

    }
}
