package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.OrderDeliveryResponse;
import br.com.fiap.delivery.exception.DeliveryException;
import br.com.fiap.delivery.model.Delivery;
import br.com.fiap.delivery.repository.DeliveryRepository;
import br.com.fiap.delivery.service.CourierService;
import br.com.fiap.delivery.service.OrderService;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DeliveryServiceImplTest {

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private OrderService orderService;

    @Mock
    private CourierService courierService;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class FindDelivery {

        @Test
        void testFindDeliveryWhenWaiting() {
            when(mongoTemplate.find(Mockito.any(), Mockito.eq(Delivery.class)))
                    .thenReturn(List.of(Util.Objects.buildDelivery()));
            ResponseEntity<List<OrderDeliveryResponse>> resp = deliveryService.findDeliveryWhenWiatingDelivery(2);
            assertThat(resp).isNotNull();
            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void testFindDeliveryWhenWaitingError() {
            when(mongoTemplate.find(Mockito.any(), Mockito.eq(Delivery.class)))
                    .thenThrow(RuntimeException.class);
            ResponseEntity<List<OrderDeliveryResponse>> resp = deliveryService.findDeliveryWhenWiatingDelivery(2);
            assertThat(resp).isNotNull();
            assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Nested
    class SendRefreshOrder {

        @Test
        void testUpdateOrder() {
            deliveryService.sendUpdateOrder(Util.Objects.buildDelivery());
            verify(orderService, times(1)).sendUpdateOrder(Mockito.any());
        }

        @Test
        void testUpdateOrderError() {
            doThrow(RuntimeException.class).when(orderService).sendUpdateOrder(Mockito.any());
            deliveryService.sendUpdateOrder(Util.Objects.buildDelivery());
            verify(deliveryRepository, times(1)).save(Mockito.any());
        }

    }

    @Nested
    class Update {

        @Test
        void testUpdateSetCourier() {
            when(courierService.findById(Mockito.anyString())).thenReturn(Util.Objects.buildCourierSaved());
            when(mongoTemplate.find(Mockito.any(), Mockito.eq(Delivery.class))).thenReturn(new ArrayList<>());
            assertThatThrownBy(() -> deliveryService.updateSetCourier("345678", "dfghjk"))
                    .isInstanceOf(DeliveryException.class)
                    .hasMessage("Entrega não localizada.");
        }

        @Test
        void testUpdateDeliveryLeft() {
            when(mongoTemplate.find(Mockito.any(), Mockito.eq(Delivery.class))).thenReturn(new ArrayList<>());
            assertThatThrownBy(() -> deliveryService.updateDeliveryLeft("345678"))
                    .isInstanceOf(DeliveryException.class)
                    .hasMessage("Entregas não localizadas");
        }


        @Test
        void testUpdateCompleted() {
            when(mongoTemplate.find(Mockito.any(), Mockito.eq(Delivery.class))).thenReturn(new ArrayList<>());
            assertThatThrownBy(() -> deliveryService.updateDeliveryCompleted("345678"))
                    .isInstanceOf(DeliveryException.class)
                    .hasMessage("Entrega não localizada.");
        }

    }

}
