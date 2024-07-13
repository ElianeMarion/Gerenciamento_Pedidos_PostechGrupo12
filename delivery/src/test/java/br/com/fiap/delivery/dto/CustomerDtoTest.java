package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomerDtoTest {
    @Test
    void testInstance() {
        CustomerDto dto = Util.Objects.buildCustomer();
        assertThat(dto.getCustomerID()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Maria da Compra");
        assertThat(dto.getCpf()).isEqualTo("95859119062");
        assertThat(dto.getPhoneNumber()).isEqualTo("11999992233");
    }
}
