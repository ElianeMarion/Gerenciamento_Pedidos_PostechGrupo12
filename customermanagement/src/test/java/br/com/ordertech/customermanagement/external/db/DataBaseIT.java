package br.com.ordertech.customermanagement.external.db;


import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class DataBaseIT {

    @Autowired
    private DataBaseJpa dataBaseJpa;

    @Nested
    class Register {
        @Test
        void shouldRegisterCustomer() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            CustomerEntity customerSaved = dataBaseJpa.registerCustomer(customerEntity);

            assertThat(customerSaved)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerSaved.getCustomerId())
                    .isNotNull();
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldFindCustomerById() {
            CustomerEntity customerFound = dataBaseJpa.findCustomerById(10L);

            assertThat(customerFound)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerFound.getCustomerId())
                    .isEqualTo(10);
        }
    }

    @Nested
    class FindByName {
        @Test
        void shouldFindCustomerByName() {
            List<CustomerEntity> customers = dataBaseJpa.findCustomerByName("JOSE DA SILVA");

            assertThat(customers)
                    .isNotNull()
                    .isNotEmpty();
            assertThat(customers)
                    .asList()
                    .element(0)
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customers.get(0).getCustomerId())
                    .isEqualTo(10);
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdateCustomer() {
            String name = "João";
            String cpf = "04412336812";
            String phoneNumber = "11998761234";

            CustomerEntity customerEntity = new CustomerEntity(name, cpf, phoneNumber);
            customerEntity.setAddress(Util.buildAddress());
            customerEntity.setCustomerId(30L);

            CustomerEntity customerUpdated = dataBaseJpa.updateCustomer(30L, customerEntity);

            assertThat(customerUpdated)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerUpdated.getCustomerId())
                    .isNotNull()
                    .isEqualTo(30);
            assertThat(customerUpdated.getName())
                    .isEqualTo("João");
            List<CustomerEntity> customers = dataBaseJpa.findCustomerByName("JOSE DA SILVA");
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDeleteCustomer() {

            dataBaseJpa.deleteCustomer(30L);

            assertThatThrownBy(
                    () -> dataBaseJpa.findCustomerById(30L))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Cliente não localizado");
        }
    }

}
