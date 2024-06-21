package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderLineDtoTest {

    @Test
    void testInstance() {
        OrderLineDto dto = Util.Objects.buildOrderLine();
        assertThat(dto.getOrderLineID()).isEqualTo(1);
        assertThat(dto.getProduct()).isNotNull().isInstanceOf(ProductDto.class);
        assertThat(dto.getQuantity()).isEqualTo(1);
        assertThat(dto.getPrice().doubleValue()).isEqualTo(1000.99);
    }

}
