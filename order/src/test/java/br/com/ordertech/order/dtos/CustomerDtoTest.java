package br.com.ordertech.order.dtos;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.CustomerDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDtoTest {
    @Test
    void testInstance() {
        CustomerDto dto = OrderHelper.buildCustomer();
        assertThat(dto.getCustomerId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("João da Silva");
        assertThat(dto.getCpf()).isEqualTo("95859119062");
        assertThat(dto.getPhoneNumber()).isEqualTo("11999992233");
    }
}
