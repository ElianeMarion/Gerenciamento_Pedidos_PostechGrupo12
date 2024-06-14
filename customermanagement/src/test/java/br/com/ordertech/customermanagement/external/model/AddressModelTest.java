package br.com.ordertech.customermanagement.external.model;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressModelTest {
    @Nested
    class Create {
        @Test
        void shouldCreateAddressModel() {
            Address address = Util.buildAddress();

            AddressModel addressModel = new AddressModel(address, 10);

            assertThat(addressModel.getAddressId()).isEqualTo(10);
            assertThat(addressModel.getStreet()).isEqualTo(address.getStreet());
            assertThat(addressModel.getNumber()).isEqualTo(address.getNumber());
            assertThat(addressModel.getComplement()).isEqualTo(address.getComplement());
            assertThat(addressModel.getCity()).isEqualTo(address.getCity());
            assertThat(addressModel.getState()).isEqualTo(address.getState());
            assertThat(addressModel.getZipcode()).isEqualTo(address.getZipcode());
            assertThat(addressModel.getSubSector()).isEqualTo(address.getSubSector());
        }
    }

    @Nested
    class ToAddress {
        @Test
        void shouldConvertModeltoEntity() {
            Address address;
            AddressModel addressModel = new AddressModel(Util.buildAddress(), 10);

            address = addressModel.toAddress();

            assertThat(addressModel.getAddressId()).isEqualTo(10);
            assertThat(addressModel.getStreet()).isEqualTo(address.getStreet());
            assertThat(addressModel.getNumber()).isEqualTo(address.getNumber());
            assertThat(addressModel.getComplement()).isEqualTo(address.getComplement());
            assertThat(addressModel.getCity()).isEqualTo(address.getCity());
            assertThat(addressModel.getState()).isEqualTo(address.getState());
            assertThat(addressModel.getZipcode()).isEqualTo(address.getZipcode());
            assertThat(addressModel.getSubSector()).isEqualTo(address.getSubSector());
        }
    }
}
