package br.com.ordertech.order.controller;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Order> orderList;
    AutoCloseable openMocks;
    @BeforeEach
    void setUp() {
        orderList = new ArrayList<>();
        Order order = OrderHelper.createOrder();
        orderList.add(order);
    }

    @Test
    void testListAll() throws Exception {
        when(orderService.getAll()).thenReturn(orderList);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getAll();
    }

    @Test
    void testCreateOrder() throws Exception {
        Order order = OrderHelper.createOrder();

        when(orderService.saveOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated());


        verify(orderService, times(1)).saveOrder(any(Order.class));
    }

    @Test
    void testCreateOrder_ProductNotAvailable() throws Exception {
        Order order = OrderHelper.createOrder();
        doThrow(new NoSuchElementException("Produto não disponível"))
                .when(orderService).saveOrder(any(Order.class));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Produto não disponível"));

        verify(orderService, times(1)).saveOrder(any(Order.class));
    }

    @Test
    void testGetCustomerByOrderId() throws Exception {
        CustomerDto customer = OrderHelper.buildCustomer();

        when(orderService.getCustomerById(anyInt())).thenReturn(customer);

        mockMvc.perform(get("/orders/1/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).getCustomerById(anyInt());
    }

    @Test
    void testUpdateStatus() throws Exception {
        Order order = OrderHelper.createOrder();
        when(orderService.updateStatus(anyLong(), anyInt())).thenReturn(order);

        mockMvc.perform(put("/orders/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService, times(1)).updateStatus(anyLong(), anyInt());
    }
}
