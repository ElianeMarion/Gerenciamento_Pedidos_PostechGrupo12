package br.com.ordertech.customermanagement.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

public class EncodeConfig {
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
