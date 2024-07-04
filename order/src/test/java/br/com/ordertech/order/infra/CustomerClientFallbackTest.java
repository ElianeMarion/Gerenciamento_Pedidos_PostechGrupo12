package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerClientFallbackTest {

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private CustomerClientFallback customerClientFallback;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById_Fallback() {
        // Define o comportamento do mock para retornar um CustomerDto vazio
        when(customerClient.getCustomerById(anyLong())).thenReturn(new CustomerDto());

        CustomerDto result = customerClientFallback.getCustomerById(1L);

        assertThat(result).isNotNull();
    }
}
