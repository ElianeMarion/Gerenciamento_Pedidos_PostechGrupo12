package br.com.ordertech.order.model;

import br.com.ordertech.order.models.Address;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

    @Test
    public void testAddressGettersAndSetters() {
        Address address = new Address();

        address.setAddressId(1L);
        address.setStreet("Avenida Paulista");
        address.setNumber(123);
        address.setComplement("Apt 4B");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setZipcode("62701080");
        address.setSubSector(1);

        assertThat(address.getAddressId()).isEqualTo(1L);
        assertThat(address.getStreet()).isEqualTo("Avenida Paulista");
        assertThat(address.getNumber()).isEqualTo(123);
        assertThat(address.getComplement()).isEqualTo("Apt 4B");
        assertThat(address.getCity()).isEqualTo("São Paulo");
        assertThat(address.getState()).isEqualTo("SP");
        assertThat(address.getZipcode()).isEqualTo("62701080");
        assertThat(address.getSubSector()).isEqualTo(1);
    }

    @Test
    public void testValidAddress() {
        Address address1 = new Address();
        address1.setAddressId(1L);
        address1.setStreet("Avenida Paulista");
        address1.setNumber(123);
        address1.setComplement("Apt 4B");
        address1.setCity("São Paulo");
        address1.setState("SP");
        address1.setZipcode("62701080");
        address1.setSubSector(1);

        Address address2 = new Address();
        address2.setAddressId(1L);
        address2.setStreet("Avenida Paulista");
        address2.setNumber(123);
        address2.setComplement("Apt 4B");
        address2.setCity("São Paulo");
        address2.setState("SP");
        address2.setZipcode("62701080");
        address2.setSubSector(1);

        assertThat(address1).isEqualTo(address2);
    }

    @Test
    public void testDiferentAddress() {
        Address address1 = new Address();
        address1.setAddressId(1L);
        address1.setStreet("Avenida Paulista");
        address1.setNumber(123);
        address1.setComplement("Apt 4B");
        address1.setCity("São Paulo");
        address1.setState("SP");
        address1.setZipcode("62701080");
        address1.setSubSector(1);

        Address address2 = new Address();
        address2.setAddressId(2L);
        address2.setStreet("Alameda Santos");
        address2.setNumber(456);
        address2.setComplement("Apt 1A");
        address2.setCity("São Paulo");
        address2.setState("SP");
        address2.setZipcode("46176070");
        address2.setSubSector(2);

        assertThat(address1).isNotEqualTo(address2);
    }

}
