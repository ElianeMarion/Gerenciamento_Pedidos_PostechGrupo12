package br.com.ordertech.order.model;

import br.com.ordertech.order.models.Address;
import br.com.ordertech.order.models.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class CustomerTest {

    @Test
    public void testCustomerGettersAndSetters() {
        Customer customer = new Customer();

        Address address = new Address();
        address.setStreet("Avenida Paulista");
        address.setNumber(123);
        address.setComplement("Apt 4B");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setZipcode("04058852");
        address.setSubSector(1);

        customer.setCustomerId(1L);
        customer.setName("José da Silva");
        customer.setCpf("123.456.789-00");
        customer.setPhoneNumber("123-456-7890");
        customer.setAddress(address);

        assertThat(customer.getCustomerId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("José da Silva");
        assertThat(customer.getCpf()).isEqualTo("123.456.789-00");
        assertThat(customer.getPhoneNumber()).isEqualTo("123-456-7890");
        assertThat(customer.getAddress()).isEqualTo(address);
    }

    @Test
    public void testEqualsCustomer() {
        Address address = new Address();
        address.setStreet("Avenida Paulista");
        address.setNumber(123);
        address.setComplement("Apt 4B");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setZipcode("04058852");
        address.setSubSector(1);

        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        customer1.setName("José da Silva");
        customer1.setCpf("123.456.789-00");
        customer1.setPhoneNumber("(11)99456-7890");
        customer1.setAddress(address);

        Customer customer2 = new Customer();
        customer2.setCustomerId(1L);
        customer2.setName("José da Silva");
        customer2.setCpf("123.456.789-00");
        customer2.setPhoneNumber("(11)99456-7890");
        customer2.setAddress(address);

        assertThat(customer1).isEqualTo(customer2);
    }

    @Test
    public void testDifferentCustomer() {
        Address address1 = new Address();
        address1.setStreet("Avenida Paulista");
        address1.setNumber(123);
        address1.setComplement("Apt 4B");
        address1.setCity("São Paulo");
        address1.setState("SP");
        address1.setZipcode("04058852");
        address1.setSubSector(1);

        Address address2 = new Address();
        address2.setStreet("Alameda Santos");
        address2.setNumber(456);
        address2.setComplement("apt 1A");
        address2.setCity("São Paulo");
        address2.setState("SP");
        address2.setZipcode("08998969");
        address2.setSubSector(2);

        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        customer1.setName("José da Silva");
        customer1.setCpf("123.456.789-00");
        customer1.setPhoneNumber("(11)99456-7890");
        customer1.setAddress(address1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customer2.setName("João Santos");
        customer2.setCpf("987.654.321-00");
        customer2.setPhoneNumber("(11)99456-7890");
        customer2.setAddress(address2);

        assertThat(customer1).isNotEqualTo(customer2);
    }
}
