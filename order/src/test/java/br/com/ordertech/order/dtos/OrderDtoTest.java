package br.com.ordertech.order.dtos;

import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.dto.OrderDto;
import br.com.ordertech.order.dto.OrderLineDto;
import br.com.ordertech.order.enums.StatusEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

    @Test
    void testInstance() {
        OrderDto dto = new OrderDto();
        dto.setOrderId(1L);
        dto.setStatus(StatusEnum.WAITING_DELIVERY);
        dto.setDeliveryDate(LocalDateTime.of(2024,5,2,15,12,59));
        dto.setPurchaseDate(LocalDateTime.of(2024,5,2,10,12,59));
        dto.setCustomer(new CustomerDto());
        dto.setOrderLines(List.of(new OrderLineDto()));
        assertThat(dto.getOrderId()).isEqualTo(1L);
        assertThat(dto.getStatus()).isEqualTo(StatusEnum.WAITING_DELIVERY);
        assertThat(dto.getDeliveryDate()).isEqualTo(LocalDateTime.of(2024,5,2,15,12,59));
        assertThat(dto.getPurchaseDate()).isEqualTo(LocalDateTime.of(2024,5,2,10,12,59));
        assertThat(dto.getCustomer()).isNotNull().isInstanceOf(CustomerDto.class);
        assertThat(dto.getOrderLines()).isNotNull().asList().element(0).isInstanceOf(OrderLineDto.class);
    }

}
