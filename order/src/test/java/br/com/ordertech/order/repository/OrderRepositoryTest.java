package br.com.ordertech.order.repository;


import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.model.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderRepositoryTest {
    @Mock
    private OrderRepository orderRepository;

    AutoCloseable openMocks;
    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldAllowCreateOrder(){
        var order = createOrder();
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        var newOrder = orderRepository.save(order);
        verify(orderRepository, times(1)).save(order);
        assertThat(newOrder)
                .isInstanceOf(Order.class)
                .isNotNull()
                .isEqualTo(order);
        assertThat(newOrder)
                .extracting(Order::getOrderId)
                .isEqualTo(order.getOrderId());
        assertThat(newOrder)
                .extracting(Order::getPurchaseDate)
                .isEqualTo(order.getPurchaseDate());
        assertThat(newOrder)
                .extracting(Order::getStatus)
                .isEqualTo(order.getStatus());
    }

    private Order createOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setStatus(StatusEnum.WAITING_SEPARATION);
        order.setCustomerId(1);
        order.setPurchaseDate(LocalDateTime.now());
        order.setDeliveryDate(null);
        order.setDeliveryAddressId(1);
        order.setOriginAddressId(1);
        order.setOrderLine(OrderHelper.addItens());
        return order;
    }


    @Test
    void shouldAllowFindOrders(){
        var order1 = createOrder();
        var order2 = createOrder();
        var ordersList = Arrays.asList(order2, order1);
        when(orderRepository.findAll()).thenReturn(ordersList);

        var list = orderRepository.findAll();

        verify(orderRepository, times(1)).findAll();
        assertThat(list)
                .hasSize(2)
                .containsExactlyInAnyOrder(order2, order1);

    }
    @Test
    void shouldAllowFindOrderById(){

        var id = 1L;
        var order = createOrder();
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));
        var orderFound = orderRepository.findById(id);

        //Assert
        assertThat(orderFound)
                .isPresent()
                .containsSame(order);
        orderFound.ifPresent(restaurantFounded -> {
            assertThat(orderFound.get().getOrderId()).isEqualTo(order.getOrderId());
            assertThat(orderFound.get().getStatus()).isEqualTo(order.getStatus());
        });
        verify(orderRepository, times(1))
                .findById(any(Long.class));

    }
}

