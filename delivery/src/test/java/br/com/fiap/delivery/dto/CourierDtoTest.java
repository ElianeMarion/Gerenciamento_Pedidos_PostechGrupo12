package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CourierDtoTest {

    @Test
    void testInstance() {
        CourierDto dto = Util.Objects.buildCourier();
        assertThat(dto.getCourierID()).isEqualTo("6670ed2661c1ca485c37ba6e");
        assertThat(dto.getCourierName()).isEqualTo("Zezão do Grau");
        assertThat(dto.getStatus()).isEqualTo("WAITING");
        assertThat(dto.getLastDelivery()).isEqualTo(LocalDateTime.of(2024,5,13,20,23,59));

    }

}
