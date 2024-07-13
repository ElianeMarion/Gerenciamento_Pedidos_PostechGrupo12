package br.com.ordertech.customermanagement.infraestructure.gateway.impl;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.infraestructure.gateway.impl.customer.CustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.ICustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.IDataBase;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CustomerGatewayTest {

    @Mock
    private IDataBase dataBase;

    private ICustomerGateway customerGateway;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        customerGateway = new CustomerGateway(dataBase);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class RegisterCustomer {
        @Test
        void shouldRegisterCustomer() {
            CustomerEntity customerEntity = Util.buildCustomerEntity();
            Address address = Util.buildAddress();
            customerEntity.setAddress(address);

            when(dataBase.registerCustomer(customerEntity))
                    .thenReturn(customerEntity);

            CustomerEntity customerRegistred = customerGateway.registerCustomer(customerEntity);

            assertThat(customerRegistred)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerRegistred.getName())
                    .isEqualTo("João");
        }
    }

    @Nested
    class FindCustomerById {
        @Test
        void shouldFindCustomerById() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(dataBase.findCustomerById(10L))
                    .thenReturn(customerEntity);

            CustomerEntity customerFound = customerGateway.findCustomerById(10L);

            assertThat(customerFound)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerFound.getCustomerId())
                    .isEqualTo(10);
            assertThat(customerFound.getName())
                    .isEqualTo("João");
        }
    }

    @Nested
    class FindCustomerByName {
        @Test
        void shouldFindCustomerByName() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
            customers.add(customerEntity);

            when(dataBase.findCustomerByName("João"))
                    .thenReturn(customers);

            List<CustomerEntity> customersFound = customerGateway.findCustomerByName("João");

            assertThat(customersFound)
                    .isNotNull()
                    .isNotEmpty();
            assertThat(customersFound.get(0).getName())
                    .isEqualTo("João");
        }
    }

    @Nested
    class UpdateCustomer {
        @Test
        void shouldUpdateCustomer() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(dataBase.updateCustomer(Long.valueOf(10L), customerEntity))
                    .thenReturn(customerEntity);

            CustomerEntity customerUpdated = customerGateway
                    .updateCustomer(Long.valueOf(10L), customerEntity);

            assertThat(customerUpdated)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerUpdated.getName())
                    .isEqualTo("João");
        }
    }

    @Nested
    class DeleteCustomer {
        @Test
        void deleteCustomer() {
            Long customerId = Long.valueOf(10);
            doNothing().when(dataBase).deleteCustomer(customerId);

            customerGateway.deleteCustomer(customerId);

            verify(dataBase, times(1)).deleteCustomer(customerId);
        }
    }

}
