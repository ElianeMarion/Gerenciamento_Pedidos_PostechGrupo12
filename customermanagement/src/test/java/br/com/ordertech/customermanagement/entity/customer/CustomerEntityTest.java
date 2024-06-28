package br.com.ordertech.customermanagement.entity.customer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerEntityTest {

    @Nested
    class CreateCustomerEntity {
        @Test
        void shouldCreateCustomerEntityTest() {
            String name = "João";
            String cpf = "04412336812";
            String phoneNumber = "11998761234";

            CustomerEntity customerEntity =  new CustomerEntity(name, cpf, phoneNumber);

            assertThat(customerEntity.getName()).isEqualTo("João");
            assertThat(customerEntity.getCpf()).isEqualTo("04412336812");
            assertThat(customerEntity.getPhoneNumber()).isEqualTo("11998761234");
        }
    }
}
