package br.com.ordertech.order.dtos;

import br.com.ordertech.order.Utils.OrderHelper;
import br.com.ordertech.order.dto.AddressDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressDtoTest {

    @Test
    void testInstance() {
        AddressDto dto = OrderHelper.buildAddress();
        assertThat(dto.getAddressID()).isEqualTo(1L);
        assertThat(dto.getStreet()).isEqualTo("Rua Leblon");
        assertThat(dto.getNumber()).isEqualTo(10);
        assertThat(dto.getComplement()).isEqualTo("A");
        assertThat(dto.getCity()).isEqualTo("Embu das Artes");
        assertThat(dto.getState()).isEqualTo("SP");
        assertThat(dto.getZipCode()).isEqualTo("06826270");
        assertThat(dto.getSubSector()).isEqualTo(2);
    }

}
