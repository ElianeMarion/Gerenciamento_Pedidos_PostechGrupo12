package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDeliveryResponseTest {

    @Test
    void testInstance() {
        OrderDeliveryResponse resp = new OrderDeliveryResponse();
        resp.setDeliveryID(Util.getId());
        resp.setCourier(new CourierDto());
        resp.setDepartureDate(LocalDateTime.of(2024,5,12,11,23,33));
        resp.setArrivalDate(LocalDateTime.of(2024,5,13,12,23,33));
        resp.setOrder(new OrderDto());
        resp.setSenderAddress(new AddressDto());
        assertThat(resp.getDeliveryID()).isEqualTo(Util.getId());
        assertThat(resp.getCourier()).isNotNull().isInstanceOf(CourierDto.class);
        assertThat(resp.getDepartureDate()).isEqualTo(LocalDateTime.of(2024,5,12,11,23,33));
        assertThat(resp.getArrivalDate()).isEqualTo(LocalDateTime.of(2024,5,13,12,23,33));
        assertThat(resp.getOrder()).isNotNull().isInstanceOf(OrderDto.class);
        assertThat(resp.getSenderAddress()).isNotNull().isInstanceOf(AddressDto.class);
    }


}
