package br.com.ordertech.order.model;

import br.com.fiap.pedido_cloud.exceptions.InvalidValueException;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("order-line")
public class OrderLine {
    @Id
    private Long orderLineId;
    private Long productId;
    private int quantity;
    private BigDecimal price;



    public OrderLine(Long orderLineId, Long productId, int quantity, BigDecimal price) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        setQuantity(quantity);
        setPrice(price);
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
