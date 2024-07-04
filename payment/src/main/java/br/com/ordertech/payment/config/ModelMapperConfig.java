package br.com.ordertech.payment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public Mapper mapper() {
        final ModelMapper modelMapper = new ModelMapper();
        return new Mapper() {

            public <D> D map(Object source, Class<D> destinationType) {
                return modelMapper.map(source, destinationType);
            }

            public void map(Object source, Object destination) {
                modelMapper.map(source, destination);
            }
        };
    }
}
