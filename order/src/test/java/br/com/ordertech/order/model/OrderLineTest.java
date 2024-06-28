package br.com.ordertech.order.model;


import br.com.ordertech.order.exceptions.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OrderLineTest {
    @Test
    void testConstructor(){

        OrderLine orderLine = new OrderLine(1L, 1l, 1);
        assertThat(orderLine).isNotNull().isInstanceOf(OrderLine.class);
        assertThat(orderLine.getOrderLineId()).isNotNull().isEqualTo(1L);
        assertThat(orderLine.getProductId()).isNotNull().isEqualTo(1L);
        assertThat(orderLine.getQuantity()).isNotNull().isEqualTo(1);
    }

    @Test
    void testGetterAndSetters_Price(){
        OrderLine orderLine = new OrderLine(1L, 1l, 1);
        orderLine.setPrice(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, orderLine.getPrice());
    }
    @Test
    void checkValidQuantity(){
        int quantity = 10;
        OrderLine orderLine = new OrderLine(1L, 1L, quantity);
        assertEquals(quantity, orderLine.getQuantity());
    }

    @Test
    void checkInvalidQuantity(){
        int quantity = -6;
        OrderLine orderLine;
        InvalidValueException invalidValueException = assertThrows(InvalidValueException.class, () -> new OrderLine(1L, 1L, quantity));
        String expectedMessage = "Quantidade inválida, o valor informado não pode ser negativo.";
        String actualMessage = invalidValueException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void checkValidPrice(){
        BigDecimal price = new BigDecimal(10.00);
        OrderLine orderLine = new OrderLine(1L, 1L, 10);
        orderLine.setPrice(price);
        assertEquals(price, orderLine.getPrice());
    }

    @Test
    void checkInvalidPrice_NegativePrice(){
        BigDecimal price = new BigDecimal(-2.0);
        OrderLine orderLine = new OrderLine(1L, 1L, 10);
        InvalidValueException invalidValueException = assertThrows(InvalidValueException.class, () -> orderLine.setPrice(price));
        String expectedMessage = "Preço inválido, o valor informado não pode ser negativo.";
        String actualMessage = invalidValueException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void check_SetPrice_Invalid_Zero() {
        OrderLine orderLine = new OrderLine(1L, 2L, 3);

        assertThrows(InvalidValueException.class, () -> orderLine.setPrice(BigDecimal.ZERO));
    }

    @Test
    void testToString() {
        OrderLine orderLine = new OrderLine(1L, 2L, 3);
        orderLine.setPrice(BigDecimal.valueOf(99.99));

        String expectedString = "OrderLine(orderLineId=1, productId=2, quantity=3, price=99.99)";
        assertEquals(expectedString, orderLine.toString());
    }
}
