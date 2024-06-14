package br.com.ordertech.customermanagement.external.db;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;
import br.com.ordertech.customermanagement.external.model.CustomerModel;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class DataBaseTest {

    @InjectMocks
    private DataBaseJpa dataBase;

    @Mock
    private CustomerRepository customerRepository;

    private AutoCloseable mocks;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        mocks.close();
    }

    @Nested
    class Register {
        @Test
        void shouldRegisterCustomer() {
            CustomerModel customerModel = new CustomerModel(Util.buildCustomerEntityFull());
            customerModel.setCustomerId(10);
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(customerRepository.save(any(CustomerModel.class)))
                    .thenReturn(customerModel);

            CustomerEntity customerSaved = dataBase.registerCustomer(customerEntity);

            assertThat(customerSaved).isNotNull()
                    .isInstanceOf(CustomerEntity.class);
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldFindById() {
            CustomerModel customerModel = new CustomerModel(Util.buildCustomerEntityFull());
            Optional<CustomerModel> optionalCustomer = Optional.of(customerModel);

            when(customerRepository.findById(any(Integer.class)))
                    .thenReturn(optionalCustomer);

            CustomerEntity customerFound = dataBase.findCustomerById(10);

            assertThat(customerFound).isNotNull()
                    .isInstanceOf(CustomerEntity.class);
        }
    }

    @Nested
    class FindByName {
        @Test
        void shouldFindByName() {
            CustomerModel customerModel = new CustomerModel(Util.buildCustomerEntityFull());
            List<CustomerModel> customers = Arrays.asList(customerModel);

            when(customerRepository.findByName("João"))
                    .thenReturn(customers);

            List<CustomerEntity> customersFound = dataBase.findCustomerByName("João");

            assertThat(customersFound).isNotNull()
                    .isNotEmpty()
                    .size().isEqualTo(1);
            assertThat(customersFound.get(0).getName())
                    .isEqualTo("João");
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdateCustomer() {
            CustomerModel customerModelIn = new CustomerModel(Util.buildCustomerEntityFull());
            customerModelIn.setCustomerId(10);
            CustomerModel customerModelOut = new CustomerModel(Util.buildCustomerEntityFull());
            customerModelOut.setCustomerId(10);
            customerModelOut.setName("João II");
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            // getReferenceById

            when(customerRepository.getReferenceById(10))
                    .thenReturn(customerModelIn);

            when(customerRepository.save(any(CustomerModel.class)))
                    .thenReturn(customerModelOut);

            CustomerEntity customerUpdated = dataBase.updateCustomer(10, customerEntity);

            assertThat(customerUpdated).isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerUpdated.getName())
                    .isEqualTo("João II");
        }

        @Test
        void shouldNotFoundCustomer() {
            /*
            CustomerModel customerModelIn = new CustomerModel(Util.buildCustomerEntityFull());
            customerModelIn.setCustomerId(10);
            CustomerModel customerModelOut = new CustomerModel(Util.buildCustomerEntityFull());
            customerModelOut.setCustomerId(10);
            customerModelOut.setName("João II");
             */
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(customerRepository.getReferenceById(10))
                    .thenThrow(new EntityNotFoundException());

            assertThatThrownBy(() -> dataBase.updateCustomer(10, customerEntity))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Cliente não localizado");
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDelete() {
            CustomerModel customerModel = new CustomerModel(Util.buildCustomerEntityFull());
            List<CustomerModel> customers = Arrays.asList(customerModel);

            doNothing()
                    .when(customerRepository)
                    .deleteById(10);

            dataBase.deleteCustomer(10);

            verify(customerRepository, times(1))
                    .deleteById(10);
        }
    }

}
