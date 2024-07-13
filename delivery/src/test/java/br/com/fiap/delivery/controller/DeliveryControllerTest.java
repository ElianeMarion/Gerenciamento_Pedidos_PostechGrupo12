package br.com.fiap.delivery.controller;

import br.com.fiap.delivery.dto.OrderDelivery;
import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import br.com.fiap.delivery.service.DeliveryService;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class DeliveryControllerTest {

    @InjectMocks
    private DeliveryController deliveryController;

    @Mock
    private DeliveryService deliveryService;

    private MockMvc mockMvc;

    private static final String PATH = "/delivery";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @Nested
    class SaveDelivery {

        @Test
        void testCreateDeliverySuccess() throws Exception {
            OrderDelivery orderDelivery = Util.Objects.buildOrderDeliveryFull();
            when(deliveryService.saveDelivery(Mockito.any())).thenReturn(Util.Objects.buildOrderDeliveryResponse());
            MvcResult result = mockMvc.perform(
                    post(PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                              .content(Util.serialize(orderDelivery)))
                    .andReturn();
            assertThat(result).isNotNull();
            assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        }

        @Test
        void testCreateDeliveryBadRequest() throws Exception {
            MvcResult result = mockMvc.perform(
                            post(PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(Util.serialize(new OrderDelivery())))
                    .andReturn();
            assertThat(result).isNotNull();
            assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

    }

    @Nested
    class FindDelivery {

        @Test
        void testFindDeliveryWhenWaitingForDelivery() throws Exception {
            when(deliveryService.findDeliveryWhenWiatingDelivery(Mockito.anyInt()))
                    .thenReturn(ResponseEntity.ok(List.of(Util.Objects.buildOrderDeliveryResponse())));
            MvcResult result = mockMvc
                    .perform(get(PATH).queryParam("subSector","2"))
                    .andReturn();
            assertThat(result).isNotNull();
            assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        }

    }


    @Nested
    class UpdateDelivery {

        @Test
        void testUpdateSetCourier() throws Exception {
            when(deliveryService.updateSetCourier(Mockito.anyString(), Mockito.anyString()))
                    .thenReturn(ResponseEntity.ok(Util.Objects.buildOrderDeliveryResponseWithCourier()));
            MvcResult mvcResult = mockMvc.perform(put(PATH)
                    .queryParam("deliveryID", "12esi123sss")
                    .queryParam("courierID","5678sdhsjd9"))
                    .andReturn();
            assertThat(mvcResult).isNotNull();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        }

        @Test
        void testUpdateStatusLeft() throws Exception {
            when(deliveryService.updateDeliveryLeft(Mockito.anyString()))
                    .thenReturn(ResponseEntity.ok(List.of(Util.Objects.buildOrderDeliveryResponseWithCourier())));
            MvcResult mvcResult = mockMvc.perform(put(PATH + "/left/5678sdhsjd9")).andReturn();
            assertThat(mvcResult).isNotNull();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        }

        @Test
        void testUpdateStatusCompleted() throws Exception {
            when(deliveryService.updateDeliveryCompleted(Mockito.anyString()))
                    .thenReturn(ResponseEntity.ok(Util.Objects.buildOrderDeliveryResponseWithCourier()));
            MvcResult mvcResult = mockMvc.perform(put(PATH + "/completed/5678sdhsjd9")).andReturn();
            assertThat(mvcResult).isNotNull();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        }

    }

}
