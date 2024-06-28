package br.com.ordertech.order.service;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.exceptions.NotFoundException;
import br.com.ordertech.order.model.OrderLine;
import br.com.ordertech.order.repository.OrderLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class OrderLineServiceTest {

    @InjectMocks
    private OrderLineService orderLineService;

    @Mock
    private OrderLineRepository orderLineRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        var orderLine = new OrderLine(1L, 1L, 1);
        orderLine.setPrice(BigDecimal.valueOf(10));
    }

    @Nested
    class FindOrderLine {
        @Test
        void testGetAll() {
            var orderLine = OrderHelper.createOrderLine();
            var orderLine2 = OrderHelper.createOrderLine();

            List<OrderLine> orders = Arrays.asList(orderLine, orderLine2);
            when(orderLineRepository.findAll()).thenReturn(orders);

            List<OrderLine> foundOrders = orderLineService.getAll();

            assertThat(foundOrders).isNotNull();
            assertThat(foundOrders.size()).isEqualTo(2);
            assertThat(foundOrders).containsExactlyInAnyOrder(orderLine, orderLine2);
        }

        @Test
        void testGetOrderLineById_OrderExists() {
            var orderLine = OrderHelper.createOrderLine();
            when(orderLineRepository.findById(anyLong())).thenReturn(Optional.of(orderLine));

            OrderLine foundOrder = orderLineService.findByOderLineId(1L);

            assertThat(foundOrder).isNotNull();
            assertThat(foundOrder.getOrderLineId()).isEqualTo(orderLine.getOrderLineId());
        }

        @Test
        void testGetOrderLineById_OrderLineNotExists() {
            when(orderLineRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> orderLineService.findByOderLineId(1L))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Item não encontrado");
        }

        @Nested
        class SaveOrder {
            @Test
            void testSaveOrderLine_Success() {

                var orderLine = OrderHelper.createOrderLine();
                when(orderLineRepository.save(any(OrderLine.class))).thenReturn(orderLine);

                OrderLine result = orderLineService.createOrderLine(orderLine);

                assertNotNull(result);
                assertEquals(orderLine.getOrderLineId(), result.getOrderLineId());
                verify(orderLineRepository, times(1)).save(orderLine);
            }

        }

        @Test
        void testUpdateOrderLine() {
            OrderLine updatedOrderLine = OrderHelper.createOrderLine();

            when(orderLineRepository.findById(1L)).thenReturn(Optional.of(updatedOrderLine));
            when(orderLineRepository.save(any(OrderLine.class))).thenReturn(updatedOrderLine);

            OrderLine result = orderLineService.updateOrderLine(updatedOrderLine);

            assertNotNull(result);
            assertEquals(updatedOrderLine.getPrice(), result.getPrice());
            assertEquals(updatedOrderLine.getQuantity(), result.getQuantity());
            verify(orderLineRepository, times(1)).findById(1L);
            verify(orderLineRepository, times(1)).save(updatedOrderLine);
        }
    }
}
