package br.com.ordertech.order.consumer;


import br.com.ordertech.order.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerClientFallback implements CustomerClient{

    @Override
    public CustomerDto getCustomerById(Integer id) {
        return new CustomerDto();
    }
}
