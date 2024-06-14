package br.com.ordertech.customermanagement.entity.customer;

import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerEntityBuildTest {
    @Nested
    class BuildCustomerEntity {
        @Test
        void shouldBuildCustomerEntityAndAddInfos () {

            CustomerEntityBuild customerEntityBuild = new CustomerEntityBuild();

            customerEntityBuild.addInfos("Jose", 012333444, 998761234);
            CustomerEntity customerEntity = customerEntityBuild.build();

            assertThat(customerEntity).isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getName()).isEqualTo("Jose");
            assertThat(customerEntity.getCpf()).isEqualTo(012333444);
            assertThat(customerEntity.getPhoneNumber()).isEqualTo(998761234);
        }

        @Test
        void shouldBuildCustomerEntityWithInfosAndAddress () {

            Address address = Util.buildAddress();

            CustomerEntityBuild customerEntityBuild = new CustomerEntityBuild();

            customerEntityBuild.addInfos("Jose", 012333444, 998761234);
            customerEntityBuild.addAddress(address);
            CustomerEntity customerEntity = customerEntityBuild.build();

            assertThat(customerEntity).isNotNull()
                    .isInstanceOf(CustomerEntity.class);

            assertThat(customerEntity.getAddress()).isEqualTo(address);
        }

        private CustomerEntityBuild buildWithInfos() {
            return new CustomerEntityBuild().addInfos("Jose", 012333444, 998761234);
        }

    }
}
