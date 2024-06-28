package br.com.ordertech.order.dtos;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.OrderLineDto;
import br.com.ordertech.order.dto.ProductDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderLineDtoTest {

    @Test
    void testInstance() {
        OrderLineDto dto = OrderHelper.buildOrderLine();
        assertThat(dto.getOrderLineID()).isEqualTo(1);
        assertThat(dto.getProduct()).isNotNull().isInstanceOf(ProductDto.class);
        assertThat(dto.getQuantity()).isEqualTo(1);
        assertThat(dto.getPrice().doubleValue()).isEqualTo(1000.99);
    }

}
