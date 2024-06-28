package br.com.ordertech.order.service;


import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.consumer.CustomerClient;
import br.com.ordertech.order.dto.AddressDto;
import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.dto.ProductDto;
import br.com.ordertech.order.dto.UpdateProductStock;
import br.com.ordertech.order.enums.StatusEnum;
import br.com.ordertech.order.enums.StautsOrderEnum;
import br.com.ordertech.order.exceptions.OrderNotFoundException;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.model.OrderLine;
import br.com.ordertech.order.producer.StockPedidoProducer;
import br.com.ordertech.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StockPedidoProducer stockPedidoProducer;

    @Mock
    private CustomerClient customerClient;

    private Order order;
    private CustomerDto customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup dummy data
        customer = new CustomerDto();
        customer.setCustomerID(1);
        AddressDto address = new AddressDto(1,"Avenida Paulista", 54,"",
                "São Paulo", "SP", "343434", 28394999);
        address.setAddressID(1);
        //customer.setAddressId(address);

        order = new Order();
        List<OrderLine> orderLines = new ArrayList<>();
        var orderLine = new OrderLine(1L,1L,1);
        orderLine.setPrice(BigDecimal.valueOf(10));
        orderLines.add(orderLine);
        order.setOrderLine(Arrays.asList(orderLine));
    }

    @Nested
    class FindOrder {
        @Test
        void testGetAll() {
            var order1 = OrderHelper.createOrder();
            var order2 = OrderHelper.createOrder();

            List<Order> orders = Arrays.asList(order1, order2);
            when(orderRepository.findAll()).thenReturn(orders);

            List<Order> foundOrders = orderService.getAll();

            assertThat(foundOrders).isNotNull();
            assertThat(foundOrders.size()).isEqualTo(2);
            assertThat(foundOrders).containsExactlyInAnyOrder(order1, order2);
        }

        @Test
        void testGetOrderById_OrderExists() {
            when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

            Order foundOrder = orderService.getOrderById(1L);

            assertThat(foundOrder).isNotNull();
            assertThat(foundOrder.getOrderId()).isEqualTo(order.getOrderId());
        }

        @Test
        void testGetOrderById_OrderNotExists() {
            when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> orderService.getOrderById(1L))
                    .isInstanceOf(OrderNotFoundException.class)
                    .hasMessage("Order not found");
        }
    }

    @Nested
    class SaveOrder {
        @Test
        void testSaveOrder_Success() {

            var customer = OrderHelper.buildCustomer();
            order = OrderHelper.createOrder();

            when(stockPedidoProducer.getPrice(anyLong())).thenReturn(BigDecimal.TEN);
            doNothing().when(stockPedidoProducer).reserveProduct(any(ProductDto.class));
            when(customerClient.getCustomerById(anyInt())).thenReturn(customer);
            when(orderRepository.save(any(Order.class))).thenReturn(order);

            Order savedOrder = orderService.saveOrder(order);

            // Verify interactions

            verify(stockPedidoProducer, times(1)).getPrice(anyLong());
     //       verify(stockPedidoProducer, times(1)).reserveProduct(any(ProductDto.class));
            verify(customerClient, times(1)).getCustomerById(anyInt());
            verify(orderRepository, times(1)).save(any(Order.class));


            // Assertions
            assertNotNull(savedOrder);
            assertEquals(StatusEnum.WAITING_SEPARATION, savedOrder.getStatus());
            assertEquals(StautsOrderEnum.WAITING_PAYMENT, savedOrder.getStatusOrder());
            assertEquals(BigDecimal.valueOf(10), savedOrder.getTotalOrderValue());
            assertNotNull(savedOrder.getPurchaseDate());
        }

        @Test
        void testSaveOrder_InsufficientStock() {
            doThrow(new RuntimeException("Stock error")).when(stockPedidoProducer).removeStock(1L, 10);

            RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
                orderService.saveOrder(order);
            });

            assertEquals("Estoque insuficiente", thrown.getMessage());
        }
    }

    @Nested
    class UpdateOrder {
        @Test
        void testUpdateStatusByStatusName_Success() {

            Order order = new Order();
            order.setOrderId(1L);
            order.setStatus(StatusEnum.WAITING_SEPARATION);

            when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
            when(orderRepository.save(any(Order.class))).thenReturn(order);

            Order result = orderService.updateStatusByStatusName(1L, StatusEnum.DELIVERY_COMPLETED);

            assertEquals(StatusEnum.DELIVERY_COMPLETED, result.getStatus());
            verify(orderRepository, times(1)).findById(anyLong());
            verify(orderRepository, times(1)).save(any(Order.class));

        }

        @Test
        void testUpdateStatus() {
            Order order = new Order();
            order.setOrderId(1L);
            order.setStatus(StatusEnum.WAITING_SEPARATION);

            when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
            when(orderRepository.save(any(Order.class))).thenReturn(order);

            Order result = orderService.updateStatus(1L, StautsOrderEnum.APPROVED);

            assertEquals(StatusEnum.WAITING_DELIVERY, order.getStatus());
            verify(orderRepository, times(1)).findById(anyLong());
            verify(orderRepository, times(1)).save(any(Order.class));
        }

        @Test
        void testUpdateStatus_OrderNotFound() {
            when(orderRepository.findById(1L)).thenReturn(Optional.empty());

            OrderNotFoundException thrown = assertThrows(OrderNotFoundException.class, () -> {
                orderService.updateStatus(1L, StatusEnum.IN_TRANSIT.ordinal());
            });

            assertEquals("Pedido não encontrado", thrown.getMessage());
        }

        @Test
        void testTotalOrderValue() {
            OrderLine orderLine1 = new OrderLine(1L, 1L, 2);
            orderLine1.setPrice(BigDecimal.TEN);
            OrderLine orderLine2 = new OrderLine(2L, 2L, 1);
            orderLine2.setPrice(new BigDecimal(20.00));

            order.setOrderLine(Arrays.asList(orderLine1, orderLine2));

            BigDecimal expectedTotal = new BigDecimal(30);
            BigDecimal actualTotal = orderService.totalOrderValue(order);

            // Assertions
            assertNotNull(actualTotal);
            assertEquals(expectedTotal, actualTotal);
        }

        @Test
        void testCanceledOrderReturnStock() {
            Order order = new Order();
            List<OrderLine> orderLines = new ArrayList<>();
            OrderLine orderLine = new OrderLine(1L, 1L, 2);
            orderLine.setPrice(BigDecimal.ONE);
            orderLines.add(orderLine);
            order.setOrderLine(orderLines);

            doNothing().when(stockPedidoProducer).incrementStockProduct(any(UpdateProductStock.class));

            orderService.canceledOrderReturnStock(order);

            ArgumentCaptor<UpdateProductStock> argumentCaptor = ArgumentCaptor.forClass(UpdateProductStock.class);
            verify(stockPedidoProducer, times(1)).incrementStockProduct(argumentCaptor.capture());

            UpdateProductStock capturedArgument = argumentCaptor.getValue();
            assertEquals(1L, capturedArgument.getProductId());
            assertEquals(2, capturedArgument.getAdditionalStock());
        }
    }
}
