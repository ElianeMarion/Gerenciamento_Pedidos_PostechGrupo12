package br.com.ordertech.customermanagement.entity.customer;

import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerEntityTest {

    @Nested
    class CreateCustomerEntity {
        @Test
        void shouldCreateCustomerEntityTest() {
            String name = "João";
            Integer cpf = Integer.valueOf(234510990);
            Integer phoneNumber = Integer.valueOf(912349876);
            Address address = Util.buildAddress();

            CustomerEntity customerEntity =  new CustomerEntity(name, cpf, phoneNumber);

            assertThat(customerEntity.getName()).isEqualTo("João");
            assertThat(customerEntity.getCpf()).isEqualTo(Integer.valueOf(234510990));
            assertThat(customerEntity.getPhoneNumber()).isEqualTo(Integer.valueOf(912349876));
        }
    }
}
