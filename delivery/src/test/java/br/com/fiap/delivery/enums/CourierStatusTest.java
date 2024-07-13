package br.com.fiap.delivery.enums;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CourierStatusTest {

    @Test
    void testInstance() {
        Stream.of(CourierStatus.values()).forEach(status -> {
            assertThat(status.getDesc()).isNotNull().isInstanceOf(String.class);
            assertThat(status.getCode()).isNotNull().isInstanceOf(Integer.class);
        });
    }

}
