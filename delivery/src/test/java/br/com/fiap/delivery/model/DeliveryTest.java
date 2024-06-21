package br.com.fiap.delivery.model;

import br.com.fiap.delivery.dto.AddressDto;
import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.dto.OrderDto;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeliveryTest {

    @Test
    void testInstance() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryID(Util.getId());
        delivery.setCourier(new Courier());
        delivery.setDepartureDate(LocalDateTime.MIN);
        delivery.setArrivalDate(LocalDateTime.MAX);
        delivery.setOrder(Util.Objects.buildOrder());
        delivery.setSenderAddress(Util.Objects.buildAddress());
        assertThat(delivery).isNotNull().isInstanceOf(Delivery.class);
        assertThat(delivery.getDeliveryID()).isEqualTo(Util.getId());
        assertThat(delivery.getCourier()).isNotNull().isInstanceOf(Courier.class);
        assertThat(delivery.getDepartureDate()).isEqualTo(LocalDateTime.MIN);
        assertThat(delivery.getArrivalDate()).isEqualTo(LocalDateTime.MAX);
        assertThat(delivery.getArrivalDate()).isEqualTo(LocalDateTime.MAX);
        assertThat(delivery.getOrder()).isNotNull().isInstanceOf(OrderDto.class);
        assertThat(delivery.getSenderAddress()).isNotNull().isInstanceOf(AddressDto.class);
    }

}
