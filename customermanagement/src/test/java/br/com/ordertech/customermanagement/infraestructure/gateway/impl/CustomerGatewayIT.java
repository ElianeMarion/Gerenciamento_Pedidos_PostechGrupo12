package br.com.ordertech.customermanagement.infraestructure.gateway.impl;


import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;
import br.com.ordertech.customermanagement.external.db.DataBaseJpa;
import br.com.ordertech.customermanagement.infraestructure.gateway.impl.customer.CustomerGateway;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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
public class CustomerGatewayIT {
    @Autowired
    private DataBaseJpa dataBaseJpa;

    private CustomerGateway customerGateway;

    @BeforeEach
    void setup() {
        this.customerGateway = new CustomerGateway(dataBaseJpa);
    }

    @Nested
    class Register {
        @Test
        void shouldRegisterCustomer() {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();
            customerEntity.setCustomerId(null);

            CustomerEntity customerSaved = customerGateway.registerCustomer(customerEntity);

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
            CustomerEntity customerEntity = customerGateway.findCustomerById(10);

            assertThat(customerEntity)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getCustomerId())
                    .isNotNull()
                    .isEqualTo(10);
        }

        @Test
        void shouldThrowException_WhenCurtomerNotFound() {
            assertThatThrownBy(
                    () -> customerGateway.findCustomerById(99))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Cliente não localizado");
        }
    }

    @Nested
    class FindByName {
        @Test
        void shouldFindCustomerByName() {
            List<CustomerEntity> customers = customerGateway.findCustomerByName("JOSE DA SILVA");

            assertThat(customers)
                    .isNotNull()
                    .isNotEmpty();
            assertThat(customers.get(0))
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customers.get(0).getName())
                    .isEqualTo("JOSE DA SILVA");
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdateCustomer() {
            String name = "João";
            Integer cpf = 234510990;
            Integer phoneNumber = 912349876;

            CustomerEntity customerEntity = new CustomerEntity(name, cpf, phoneNumber);
            customerEntity.setAddress(Util.buildAddress());
            customerEntity.setCustomerId(30);

            CustomerEntity customerUpdated = customerGateway.updateCustomer(30, customerEntity);

            assertThat(customerUpdated)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerUpdated.getCustomerId())
                    .isNotNull()
                    .isEqualTo(30);
            assertThat(customerUpdated.getName())
                    .isEqualTo("João");
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDeleteCustomer() {

            customerGateway.deleteCustomer(30);

            assertThatThrownBy(
                    () -> customerGateway.findCustomerById(30))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Cliente não localizado");
        }
    }

}
