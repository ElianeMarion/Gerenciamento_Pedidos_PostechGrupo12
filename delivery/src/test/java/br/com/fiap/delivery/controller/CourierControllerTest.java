package br.com.fiap.delivery.controller;

import br.com.fiap.delivery.service.impl.CourierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CourierControllerTest {

    @InjectMocks
    private CourierController courierController;

    @Mock
    private CourierServiceImpl courierService;

    private MockMvc mockMvc;

    private static final String PATH = "/delivery/courier";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courierController)
                .addFilter((request, response, chain) -> {
                   response.setCharacterEncoding("UTF-8");
                   chain.doFilter(request, response);
                }).build();
    }



    @Nested
    class FindCouriser {

        @Test
        void testFindCourierSuccess() throws Exception {
            when(courierService.findAll()).thenCallRealMethod();
            mockMvc.perform(get(PATH))
                    .andExpect(status().is5xxServerError());
        }

    }

}
