package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AddressDtoTest {

    @Test
    void testInstance() {
        AddressDto dto = Util.Objects.buildAddress();
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
