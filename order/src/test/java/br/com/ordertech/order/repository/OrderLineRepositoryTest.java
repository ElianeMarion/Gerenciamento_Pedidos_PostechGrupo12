package br.com.ordertech.order.repository;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.models.Order;
import br.com.ordertech.order.models.OrderLine;
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

public class OrderLineRepositoryTest {

    @Mock
    private OrderLineRepository orderLineRepository;

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
        var orderLine = OrderHelper.createOrderLine();
        when(orderLineRepository.save(any(OrderLine.class))).thenReturn(orderLine);
        var newOrderLine = orderLineRepository.save(orderLine);
        verify(orderLineRepository, times(1)).save(orderLine);
        assertThat(newOrderLine)
                .isInstanceOf(OrderLine.class)
                .isNotNull()
                .isEqualTo(orderLine);
        assertThat(newOrderLine)
                .extracting(OrderLine::getOrderLineId)
                .isEqualTo(orderLine.getOrderLineId());
        assertThat(newOrderLine)
                .extracting(OrderLine::getProductId)
                .isEqualTo(orderLine.getProductId());
        assertThat(newOrderLine)
                .extracting(OrderLine::getPrice)
                .isEqualTo(orderLine.getPrice());
    }

    


    @Test
    void shouldAllowFindOrders(){
        var orderLine1 = OrderHelper.createOrderLine();
        var orderLine2 = OrderHelper.createOrderLine();
        var ordersList = Arrays.asList(orderLine1, orderLine2);
        when(orderLineRepository.findAll()).thenReturn(ordersList);

        var list = orderLineRepository.findAll();

        verify(orderLineRepository, times(1)).findAll();
        assertThat(list)
                .hasSize(2)
                .containsExactlyInAnyOrder(orderLine1, orderLine2);

    }
    @Test
    void shouldAllowFindOrderById(){

        var id = 1L;
        var orderLine = OrderHelper.createOrderLine();
        when(orderLineRepository.findById(any(Long.class))).thenReturn(Optional.of(orderLine));
        var orderLineFound = orderLineRepository.findById(id);

        //Assert
        assertThat(orderLineFound)
                .isPresent()
                .containsSame(orderLine);
        orderLineFound.ifPresent(restaurantFounded -> {
            assertThat(orderLineFound.get().getOrderLineId()).isEqualTo(orderLine.getOrderLineId());
            assertThat(orderLineFound.get().getProductId()).isEqualTo(orderLine.getProductId());
        });
        verify(orderLineRepository, times(1))
                .findById(any(Long.class));

    }
}
