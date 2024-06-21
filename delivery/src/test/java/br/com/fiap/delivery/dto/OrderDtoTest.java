package br.com.fiap.delivery.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDtoTest {

    @Test
    void testInstance() {
        OrderDto dto = new OrderDto();
        dto.setOrderID(1L);
        dto.setStatus("Aguardando separação");
        dto.setDeliveryDate(LocalDateTime.of(2024,5,2,15,12,59));
        dto.setPurchaseDate(LocalDateTime.of(2024,5,2,10,12,59));
        dto.setCustomer(new CustomerDto());
        dto.setOrderLines(List.of(new OrderLineDto()));
        assertThat(dto.getOrderID()).isEqualTo(1L);
        assertThat(dto.getStatus()).isEqualTo("Aguardando separação");
        assertThat(dto.getDeliveryDate()).isEqualTo(LocalDateTime.of(2024,5,2,15,12,59));
        assertThat(dto.getPurchaseDate()).isEqualTo(LocalDateTime.of(2024,5,2,10,12,59));
        assertThat(dto.getCustomer()).isNotNull().isInstanceOf(CustomerDto.class);
        assertThat(dto.getOrderLines()).isNotNull().asList().element(0).isInstanceOf(OrderLineDto.class);
    }

}
