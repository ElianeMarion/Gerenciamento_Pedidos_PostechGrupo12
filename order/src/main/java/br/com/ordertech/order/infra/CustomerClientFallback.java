package br.com.ordertech.order.infra;


import br.com.ordertech.order.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerClientFallback implements CustomerClient{

    @Override
    public CustomerDto getCustomerById(Long id) {
        return new CustomerDto();
    }
}
