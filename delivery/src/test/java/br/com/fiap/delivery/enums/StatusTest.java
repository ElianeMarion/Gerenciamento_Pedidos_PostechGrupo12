package br.com.fiap.delivery.enums;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StatusTest {

    @Test
    void testValues() {
        Stream.of(Status.values()).forEach(s -> {
            assertThat(s.getDesc()).isNotBlank().isInstanceOf(String.class);
            assertThat(s.getCode()).isNotNull().isInstanceOf(Integer.class);
        });
    }

}
