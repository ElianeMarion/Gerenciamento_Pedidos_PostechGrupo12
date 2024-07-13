package br.com.ordertech.order.controller;

import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.models.Order;
import br.com.ordertech.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testListAllOrders() throws Exception {
        // Mocking service response
        when(orderService.getAll()).thenReturn(Collections.singletonList(new Order()));

        // Performing GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateOrder_Success() throws Exception {
        // Mocking request and service response
        Order mockOrder = new Order();
        when(orderService.saveOrder(any(Order.class))).thenReturn(mockOrder);

        // Performing POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateOrder_ProductNotAvailable() throws Exception {
        // Mocking service to throw NoSuchElementException
        when(orderService.saveOrder(any(Order.class))).thenThrow(new NoSuchElementException("Produto não disponível"));

        // Performing POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetCustomerByOrderId() throws Exception {
        // Mocking service response
        CustomerDto mockCustomer = new CustomerDto();
        when(orderService.getCustomerById(anyLong())).thenReturn(mockCustomer);

        // Performing GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        // Mocking service response
        Order mockOrder = new Order();
        when(orderService.updateStatus(anyLong(), anyInt())).thenReturn(mockOrder);

        // Performing PUT request
        mockMvc.perform(MockMvcRequestBuilders.put("/orders/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
