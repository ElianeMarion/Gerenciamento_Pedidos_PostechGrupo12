package br.com.fiap.delivery.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDeliveryTest {

    @Test
    void testInstance() {
        OrderDelivery dto = new OrderDelivery();
        dto.setOrder(new OrderDto());
        dto.setSenderAddress(new AddressDto());
        assertThat(dto.getOrder()).isNotNull().isInstanceOf(OrderDto.class);
        assertThat(dto.getSenderAddress()).isNotNull().isInstanceOf(AddressDto.class);
    }

}
