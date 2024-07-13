package br.com.ordertech.order.infra;

import br.com.ordertech.order.dto.CustomerDto;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {CustomerClientFallback.class})
public class CustomerClientTest {

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private CustomerClientFallback customerClientFallback;

    @Test
    public void testGetCustomerById_Success() {
        CustomerDto simulatedCustomerDto = new CustomerDto();
        simulatedCustomerDto.setCustomerId(1L);
        simulatedCustomerDto.setName("José dos Santos");

        when(customerClient.getCustomerById(anyLong())).thenReturn(simulatedCustomerDto);

        CustomerDto result = customerClient.getCustomerById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getCustomerId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("José dos Santos");
    }

}
