package br.com.ordertech.order.controller;

import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.models.Order;
import br.com.ordertech.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderControllerTest2 {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllOrders() {
        // Mocking service response
        List<Order> mockOrders = Collections.singletonList(new Order());
        when(orderService.getAll()).thenReturn(mockOrders);

        // Calling controller method
        ResponseEntity<List<Order>> responseEntity = orderController.listAll();

        // Verifying the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockOrders, responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_Success() {
        // Mocking request and service response
        Order mockOrder = new Order();
        when(orderService.saveOrder(any(Order.class))).thenReturn(mockOrder);

        // Calling controller method
        ResponseEntity<?> responseEntity = orderController.createOrder(mockOrder);

        // Verifying the result
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockOrder, responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_ProductNotAvailable() {
        // Mocking service to throw NoSuchElementException
        doThrow(new NoSuchElementException("Produto não disponível")).when(orderService).saveOrder(any(Order.class));

        // Calling controller method
        ResponseEntity<?> responseEntity = orderController.createOrder(new Order());

        // Verifying the result
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Produto não disponível", responseEntity.getBody());
    }

    @Test
    public void testGetCustomerByOrderId() {
        // Mocking service response
        CustomerDto mockCustomer = new CustomerDto();
        when(orderService.getCustomerById(anyLong())).thenReturn(mockCustomer);

        // Calling controller method
        ResponseEntity<CustomerDto> responseEntity = orderController.getCustomerByOrderId(1);

        // Verifying the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCustomer, responseEntity.getBody());
    }

    @Test
    public void testUpdateStatus() {
        // Mocking service response
        Order mockOrder = new Order();
        when(orderService.updateStatus(anyLong(), anyInt())).thenReturn(mockOrder);

        // Calling controller method
        ResponseEntity<Order> responseEntity = orderController.updateStatus(1L, 1);

        // Verifying the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockOrder, responseEntity.getBody());
    }

}
