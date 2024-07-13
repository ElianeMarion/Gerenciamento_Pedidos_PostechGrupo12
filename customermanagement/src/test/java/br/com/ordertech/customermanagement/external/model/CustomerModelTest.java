package br.com.ordertech.customermanagement.external.model;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerModelTest {
    @Nested
    class Create {
        @Test
        void shouldCreateCustomerModel() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            CustomerModel customerModel = new CustomerModel(customerEntity);

            assertThat(customerModel.getCustomerId()).isNull();
            assertThat(customerModel.getName()).isEqualTo(customerEntity.getName());
            assertThat(customerModel.getCpf()).isEqualTo(customerEntity.getCpf());
            assertThat(customerModel.getPhoneNumber()).isEqualTo(customerEntity.getPhoneNumber());
            assertThat(customerModel.getAddressModel().toAddress().getStreet())
                    .isEqualTo(customerEntity.getAddress().getStreet());
        }
    }

    @Nested
    class toAddress {
        @Test
        void shouldConvertModeltoEntity() {

            CustomerModel customerModel = new CustomerModel(Util.buildCustomerEntityFull());

            CustomerEntity customerEntity = customerModel.toEntity();

            assertThat(customerModel.getCustomerId()).isNull();
            assertThat(customerModel.getName()).isEqualTo(customerEntity.getName());
            assertThat(customerModel.getCpf()).isEqualTo(customerEntity.getCpf());
            assertThat(customerModel.getPhoneNumber()).isEqualTo(customerEntity.getPhoneNumber());
            assertThat(customerModel.getAddressModel().toAddress().getStreet())
                    .isEqualTo(customerEntity.getAddress().getStreet());
        }
    }
}
