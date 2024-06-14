package br.com.ordertech.customermanagement.entity.customer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

    @Nested
    class CreateAddress {
        @Test
        void shouldCreateAddress() {
            String street = "Rua das Flores";
            Integer number = Integer.valueOf(132);
            String complement = "Bloco E apto 42";
            String city = "Valinhos";
            String state = "São Paulo";
            Integer zipcode = Integer.valueOf(6020030);

            Address address = new Address(street, number, complement, city, state, zipcode);

            assertThat(address.getStreet()).isEqualTo("Rua das Flores");
            assertThat(address.getNumber()).isEqualTo(Integer.valueOf(132));
            assertThat(address.getComplement()).isEqualTo("Bloco E apto 42");
            assertThat(address.getCity()).isEqualTo("Valinhos");
            assertThat(address.getState()).isEqualTo("São Paulo");
            assertThat(address.getZipcode()).isEqualTo(Integer.valueOf(6020030));
            assertThat(address.getSubSector()).isEqualTo(Integer.valueOf(0));
        }
    }
}
