package br.com.ordertech.customermanagement.infraestructure.controller;

import br.com.ordertech.customermanagement.entity.customer.Address;
import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.infraestructure.gateway.impl.customer.CustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.ICustomerGateway;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.IDataBase;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
import br.com.ordertech.customermanagement.usecase.CustomerUseCase;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CustomerControllerTest {
    @Mock
    private IDataBase dataBase;

    @Mock
    private ICustomerGateway customerGateway;

    private CustomerController customerController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        customerController = new CustomerController();
        customerGateway = new CustomerGateway(dataBase);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class Register {
        @Test
        void shouldRegister() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            CustomerRecord customerRecord = Util.buildCustomerRecord();

            when(dataBase.registerCustomer(any(CustomerEntity.class)))
                    .thenReturn(customerEntity);

            CustomerEntity customerRegistred = customerController.register(customerRecord, dataBase);

            assertThat(customerRegistred)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerRegistred.getCustomerId())
                    .isEqualTo(10);
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldFinById() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(dataBase.findCustomerById(10))
                    .thenReturn(customerEntity);

            CustomerEntity customerFound = customerController.findById(10, dataBase);

            assertThat(customerFound)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerFound.getCustomerId())
                    .isEqualTo(10);
        }
    }

    @Nested
    class FindByName{
        @Test
        void shouldFindByName() {
            CustomerEntity customerFound = Util.buildCustomerEntityFull();
            List<CustomerEntity> customers = Arrays.asList(customerFound);

            when(dataBase.findCustomerByName("João"))
                    .thenReturn(customers);

            List<CustomerEntity> customersFound = customerController.findByName("João", dataBase);

            assertThat(customersFound)
                    .isNotNull()
                    .isNotEmpty();
            assertThat(customersFound)
                    .size().isEqualTo(1);
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdate() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            CustomerRecord customerRecord = Util.buildCustomerRecord();

            when(dataBase.updateCustomer(any(Integer.class), any(CustomerEntity.class)))
                    .thenReturn(customerEntity);

            CustomerEntity customerUpdate = customerController.update(10, customerRecord, dataBase);

            assertThat(customerUpdate)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerUpdate.getCustomerId())
                    .isEqualTo(10);
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDelete() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            doNothing()
                    .when(dataBase).deleteCustomer(10);

            customerController.delete(10, dataBase);

            verify(dataBase, times(1)).deleteCustomer(10);
        }
    }


}
