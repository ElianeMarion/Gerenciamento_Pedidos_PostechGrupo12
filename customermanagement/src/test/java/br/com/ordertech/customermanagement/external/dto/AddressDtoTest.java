package br.com.ordertech.customermanagement.external.dto;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.AddressRecord;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressDtoTest {
    @Nested
    class Create {
        @Test
        void shouldCreateAddressDto() {
            Address address = Util.buildAddress();

            AddressDto addressDto = new AddressDto(address);

            assertThat(addressDto.getStreet()).isEqualTo(address.getStreet());
            assertThat(addressDto.getNumber()).isEqualTo(address.getNumber());
            assertThat(addressDto.getComplement()).isEqualTo(address.getComplement());
            assertThat(addressDto.getCity()).isEqualTo(address.getCity());
            assertThat(addressDto.getState()).isEqualTo(address.getState());
            assertThat(addressDto.getZipcode()).isEqualTo(address.getZipcode());
            assertThat(addressDto.getSubSector()).isEqualTo(address.getSubSector());
        }
    }

    @Nested
    class ToRecord {
        @Test
        void shouldConvertDtoToRecord() {
            Address address;
            AddressDto addressDto = new AddressDto(Util.buildAddress());

            AddressRecord addressRecord = addressDto.toRecord();

            assertThat(addressDto.getStreet()).isEqualTo(addressRecord.street());
            assertThat(addressDto.getNumber()).isEqualTo(addressRecord.number());
            assertThat(addressDto.getComplement()).isEqualTo(addressRecord.complement());
            assertThat(addressDto.getCity()).isEqualTo(addressRecord.city());
            assertThat(addressDto.getState()).isEqualTo(addressRecord.state());
            assertThat(addressDto.getZipcode()).isEqualTo(addressRecord.zipcode());
            assertThat(addressDto.getSubSector()).isEqualTo(addressRecord.subSector());
        }
    }
}
