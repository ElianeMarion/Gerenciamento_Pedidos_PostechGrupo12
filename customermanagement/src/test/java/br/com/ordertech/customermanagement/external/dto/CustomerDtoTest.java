package br.com.ordertech.customermanagement.external.dto;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDtoTest {
    @Nested
    class Create {
        @Test
        void shouldCreateCustomerDto() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            CustomerDto customerDto = new CustomerDto(customerEntity);

            assertThat(customerDto)
                    .isNotNull()
                    .isInstanceOf(CustomerDto.class);

            assertThat(customerDto.getCustomerId()).isEqualTo(customerEntity.getCustomerId());
            assertThat(customerDto.getName()).isEqualTo(customerEntity.getName());
            assertThat(customerDto.getCpf()).isEqualTo(customerEntity.getCpf());
            assertThat(customerDto.getPhoneNumber()).isEqualTo(customerEntity.getPhoneNumber());
            assertThat(customerDto.getAddress()).isNotNull();
        }
    }

    @Nested
    class ToRecord {
        @Test
        void shouldConvertDtoToRecord() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            CustomerDto customerDto = new CustomerDto(customerEntity);

            CustomerRecord customerRecord = customerDto.toRecord();

            assertThat(customerRecord)
                    .isNotNull()
                    .isInstanceOf(CustomerRecord.class);

            assertThat(customerDto.getName()).isEqualTo(customerRecord.name());
            assertThat(customerDto.getCpf()).isEqualTo(customerRecord.cpf());
            assertThat(customerDto.getPhoneNumber()).isEqualTo(customerRecord.phoneNumber());

            assertThat(customerRecord.address()).isNotNull();
        }
    }
}
