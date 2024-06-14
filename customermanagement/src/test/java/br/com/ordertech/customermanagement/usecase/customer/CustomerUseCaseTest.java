package br.com.ordertech.customermanagement.usecase.customer;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntityBuild;
import br.com.ordertech.customermanagement.usecase.CustomerUseCase;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CustomerUseCaseTest {
    @Mock
    private CustomerEntityBuild customerEntityBuild;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class RegisterCustomer {
        @Test
        void shouldRegisterCustomer() {
            Address address = Util.buildAddress();
            CustomerEntity ce = Util.buildCustomerEntity();
            ce.setAddress(address);

            when(customerEntityBuild.build()).thenReturn(ce);

            CustomerEntity customerEntity = CustomerUseCase.registerCustomer("Jose",
                    12123123, 998761234, address);

            assertThat(customerEntity)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getName()).isEqualTo("Jose");
        }

        @Test
        void shouldFindCustomerByID() {
            Integer id = Integer.valueOf(10);

            assertThat(CustomerUseCase.findCustomerById(id))
                    .isNotNull()
                    .isInstanceOf(Integer.class);

            assertThat(CustomerUseCase.findCustomerById(id)).isEqualTo(10);
        }

        @Test
        void shouldFindCustomerByName() {
            String name = "Jose";

            assertThat(CustomerUseCase.findCustomerByName(name))
                    .isNotNull()
                    .isInstanceOf(String.class);

            assertThat(CustomerUseCase.findCustomerByName(name)).isEqualTo("Jose");
        }

        @Test
        void shouldUpdateCustomer() {
            Address address = Util.buildAddress();
            CustomerEntity ce = Util.buildCustomerEntity();
            ce.setAddress(address);

            when(customerEntityBuild.build()).thenReturn(ce);

            CustomerEntity customerEntity = CustomerUseCase.updateCustomer(10, "Jose",
                    12123123, 998761234, address);

            assertThat(customerEntity)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getName()).isEqualTo("Jose");
        }

        @Test
        void shouldDeleteCustomer() {
            Integer id = Integer.valueOf(10);

            assertThat(CustomerUseCase.deleteCustomer(id))
                    .isNotNull()
                    .isInstanceOf(Integer.class);

            assertThat(CustomerUseCase.deleteCustomer(id)).isEqualTo(10);
        }

        @Test
        void shouldLocaleCustomers() {
            Address address = Util.buildAddress();
            CustomerEntity ce = Util.buildCustomerEntity();
            ce.setAddress(address);

            List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
            customers.add(ce);

            List<CustomerEntity> customersResult = CustomerUseCase.locateCustomers(customers);

            assertThat(customersResult)
                    .isNotNull()
                    .hasSize(1);
        }
    }

}
