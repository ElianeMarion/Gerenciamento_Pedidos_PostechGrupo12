package br.com.ordertech.order.controller;


import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.controller.OrderController;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest2 {

    private MockMvc mockMvc;
    @Mock
    private OrderService orderService;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks = MockitoAnnotations.openMocks(this);
        OrderController orderController = new OrderController(orderService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }



    @Nested
    class CreateOrder {
        @Test
        void shouldCreateOrder() throws Exception {
            var order = OrderHelper.createOrder();
            when(orderService.saveOrder(order)).thenAnswer(p -> p.getArgument(0));

            mockMvc.perform(post("/orders")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

            verify(orderService, times(1)).saveOrder(any(Order.class));
        }
    }

}
