package br.com.ordertech.customermanagement.external.config;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;

public class EncondeConfigTest {
    @Nested
    class Create {
        @Test
        void shouldCreate() {
            EncodeConfig encodeConfig = new EncodeConfig();

            CharacterEncodingFilter encodingFilter = encodeConfig.characterEncodingFilter();

            assertThat(encodingFilter)
                    .isNotNull()
                    .isInstanceOf(CharacterEncodingFilter.class);
        }
    }
}
