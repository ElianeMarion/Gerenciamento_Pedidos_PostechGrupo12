package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductDtoTest {

    @Test
    void testInstance() {
        ProductDto dto = Util.Objects.buildProduct();
        assertThat(dto.getProductID()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("Motorola Edge 30 Neo Dual SIM 128 GB very peri 8 GB RAM");
        assertThat(dto.getDescription()).isEqualTo("Duas câmeras traseiras de 64 Mpx/13 Mpx, memória interna de 128GB...");
        assertThat(dto.getPrice().doubleValue()).isEqualTo(2450.0);
        assertThat(dto.getQuantityStock()).isEqualTo(100);
    }

}
