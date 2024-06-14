package br.com.ordertech.customermanagement.infraestructure.controller;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.entity.customer.exception.CustomerNotFoundException;
import br.com.ordertech.customermanagement.external.db.DataBaseJpa;
import br.com.ordertech.customermanagement.external.dto.CustomerDto;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
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
public class CustomerControllerIT {
    @Autowired
    private DataBaseJpa dataBaseJpa;

    private CustomerController customerController;

    @BeforeEach
    void setup() {
        this.customerController = new CustomerController();
    }

    @Nested
    class Register {
        @Test
        void shouldRegisterCurtomer() {
            CustomerRecord customerRecord = Util.buildCustomerRecord();

            CustomerEntity customerEntity = customerController.register(customerRecord, dataBaseJpa);

            assertThat(customerEntity)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getCustomerId())
                    .isNotNull();
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldFindCurtomerById() {

            CustomerEntity customerEntity = customerController.findById(10, dataBaseJpa);

            assertThat(customerEntity)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getCustomerId())
                    .isNotNull()
                    .isEqualTo(10);
            assertThat(customerEntity.getName())
                    .isEqualTo("JOSE DA SILVA");
        }

        @Test
        void shouldThrowException_WhenCurtomerNotFound() {

            assertThatThrownBy(
                    () -> customerController.findById(99, dataBaseJpa))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Cliente não localizado");
        }
    }

    @Nested
    class FindByName {
        @Test
        void shouldFindCurtomerByName() {

            List<CustomerEntity> customers = customerController.findByName("JOSE DA SILVA", dataBaseJpa);

            assertThat(customers)
                    .isNotNull()
                    .isNotEmpty();
            assertThat(customers.get(0))
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customers.get(0).getCustomerId())
                    .isNotNull()
                    .isEqualTo(10);
            assertThat(customers.get(0).getName())
                    .isEqualTo("JOSE DA SILVA");
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdateCurtomer() {
            CustomerDto customerDto = Util.buildCustomerDto();;
            customerDto.setName("JOSE DA SILVA II");
            CustomerRecord customerRecord = customerDto.toRecord();

            CustomerEntity customerEntity = customerController.update(10, customerRecord, dataBaseJpa);

            assertThat(customerEntity)
                    .isNotNull()
                    .isInstanceOf(CustomerEntity.class);
            assertThat(customerEntity.getCustomerId())
                    .isEqualTo(10);
            assertThat(customerEntity.getName())
                    .isEqualTo("JOSE DA SILVA II");
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDeleteCurtomer() {

            customerController.delete(10, dataBaseJpa);

            assertThatThrownBy(
                    () -> customerController.findById(10, dataBaseJpa))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("Cliente não localizado");
        }
    }

}
