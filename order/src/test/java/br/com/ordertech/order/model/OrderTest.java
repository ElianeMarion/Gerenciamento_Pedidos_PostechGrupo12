package br.com.ordertech.order.model;

import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StatusOrderEnum;
import br.com.ordertech.order.exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTest {

    Order order = new Order();

    @Test
    void testInstance(){
        Order order = new Order();
        order.setOrderId(1l);
        order.setStatusOrder(StatusOrderEnum.WAITING_PAYMENT);
        order.setStatus(StatusEnum.WAITING_DELIVERY);
        order.setCustomerId(1);
        order.setDeliveryAddressId(456);
        order.setOriginAddressId(789);
        order.setTotalOrderValue(BigDecimal.valueOf(100.50));

        List<OrderLine> orderLines = new ArrayList<>();
        var orderLine = new OrderLine(1L,1L,2);
        orderLine.setPrice(BigDecimal.valueOf(100.50));
        orderLines.add(orderLine);

        order.setOrderLine(orderLines);

        assertThat(order).isNotNull().isInstanceOf(Order.class);
        assertThat(order.getOrderId()).isNotNull().isEqualTo(1l);
        assertThat(order.getCustomerId()).isNotNull().isEqualTo(1);
        assertThat(order.getStatusOrder()).isNotNull().isEqualTo(StatusOrderEnum.WAITING_PAYMENT);
        assertThat(order.getStatus()).isNotNull().isEqualTo(StatusEnum.WAITING_DELIVERY);
        assertEquals(789, order.getOriginAddressId());
        assertEquals(BigDecimal.valueOf(100.50), order.getTotalOrderValue());
        assertNotNull(order.getOrderLine());
        assertEquals(1, order.getOrderLine().size());

    }
    @Test
    void check_setPurchaseDate_ValidPurchaseDate(){
        LocalDateTime validDate = LocalDateTime.now().plusDays(2);
        order.setPurchaseDate(validDate);
        assertEquals(validDate, order.getPurchaseDate());
    }

    @Test
    void check_setPurchaseDate_InValidPurchaseDate(){
        LocalDateTime invalidDate = LocalDateTime.now().minusDays(2);
        InvalidDateException invalidDateException = assertThrows(InvalidDateException.class, () -> order.setPurchaseDate(invalidDate));
        String expectedMessage = "A data da compra não pode ser menor que a data atual.";
        String actualMessage = invalidDateException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void check_validarData_ValidDeliveryDate(){
        order.setDeliveryDate(null);
        assertNull(order.getDeliveryDate());
    }

    @Test
    void testSetPurchaseDate_WithCurrentDate() {
        Order order = new Order();
        LocalDateTime currentDate = LocalDateTime.now().plusSeconds(5000);

        assertDoesNotThrow(() -> order.setPurchaseDate(currentDate));
    }

    @Test
    void testToString() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setCustomerId(123);
        order.setPurchaseDate(LocalDateTime.now());
        order.setDeliveryDate(LocalDateTime.now().plusDays(3));
        order.setStatus(StatusEnum.WAITING_SEPARATION);
        order.setDeliveryAddressId(456);
        order.setOriginAddressId(789);
        order.setStatusOrder(StatusOrderEnum.WAITING_PAYMENT);


        String expectedString = "Order{" +
                "orderId=" + order.getOrderId() +
                ", customerId=" + order.getCustomerId() +
                ", purchaseDate=" + order.getPurchaseDate() +
                ", deliveryDate=" + order.getDeliveryDate() +
                ", status=" + order.getStatus() +
                ", deliveryAdressId=" + order.getDeliveryAddressId() +
                ", originAdressId=" + order.getOriginAddressId() +
                ", orderLine=" + order.getOrderLine() +
                ", statusOrder = " + order.getStatusOrder() +
                '}';

        assertEquals(expectedString, order.toString());
    }

}
