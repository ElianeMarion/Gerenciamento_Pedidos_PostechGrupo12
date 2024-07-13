package br.com.ordertech.customermanagement.external.api;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.external.db.DataBaseJpa;
import br.com.ordertech.customermanagement.infraestructure.controller.CustomerController;
import br.com.ordertech.customermanagement.infraestructure.gateway.interfaces.customer.IDataBase;
import br.com.ordertech.customermanagement.infraestructure.presenter.customer.CustomerRecord;
import br.com.ordertech.customermanagement.utilsbytest.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerApiControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DataBaseJpa dataBaseJpa;

    @Mock
    private CustomerController customerController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        CustomerApiController customerApiController = new CustomerApiController(dataBaseJpa, customerController);
        mockMvc = MockMvcBuilders.standaloneSetup(customerApiController)
                .addFilters((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                })
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class Register {
        @Test
        void shouldRegisterCustomer() throws Exception {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(customerController.register(any(CustomerRecord.class), any(IDataBase.class)))
                    .thenReturn(customerEntity);

            // Act + Assert
            mockMvc.perform(post("/customer")
                            .contentType("application/Json")
                            .content(Util.asJsonString(Util.buildCustomerDto())))
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldFindCustomerById() throws Exception {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();

            when(customerController.findById(any(Long.class), any(IDataBase.class)))
                    .thenReturn(customerEntity);

            // Act + Assert
            mockMvc.perform(get("/customer/{id}", 10))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class FindByName {
        @Test
        void shouldFindCustomerByName() throws Exception {
            CustomerEntity customerEntity = Util.buildCustomerEntityFull();
            List<CustomerEntity> customers = Arrays.asList(customerEntity);

            when(customerController.findByName(any(String.class), any(IDataBase.class)))
                    .thenReturn(customers);

            // Act + Assert
            mockMvc.perform(get("/customer/by-name/{name}", "João"))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Update {
        @Test
        void shouldUpdateCustomer() throws Exception {
            CustomerEntity customerEntityOut = Util.buildCustomerEntityFull();

            when(customerController.update(any(Long.class), any(CustomerRecord.class), any(IDataBase.class)))
                    .thenReturn(customerEntityOut);

            // Act + Assert
            mockMvc.perform(put("/customer/{id}", 10)
                            .contentType("application/Json")
                            .content(Util.asJsonString(Util.buildCustomerDto())))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDeleteCustomer() throws Exception {

            doNothing()
                    .when(customerController).delete(any(Long.class), any(IDataBase.class));

            // Act + Assert
            mockMvc.perform(delete("/customer/{id}", 10))
                    .andExpect(status().isNoContent());
        }
    }

}
