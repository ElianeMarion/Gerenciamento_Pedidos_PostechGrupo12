package br.com.ordertech.order.dtos;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.ProductDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDtoTest {

    @Test
    void testInstance() {
        ProductDto dto = OrderHelper.buildProduct();
        assertThat(dto.getProductID()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("Samsung A72 Dual SIM 128 GB very peri 8 GB RAM");
        assertThat(dto.getDescription()).isEqualTo("Cinco câmeras traseiras de 64 Mpx/13 Mpx, memória interna de 128GB...");
        assertThat(dto.getPrice().doubleValue()).isEqualTo(2450.0);
        assertThat(dto.getQuantityStock()).isEqualTo(100);
    }

}
