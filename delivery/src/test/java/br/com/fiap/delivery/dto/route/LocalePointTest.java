package br.com.fiap.delivery.dto.route;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalePointTest {

    @Test
    void testInstance() {
        LocalePoint localePoint = new LocalePoint();
        localePoint.setAddress("rua leblon");
        assertThat(localePoint).isNotNull().isInstanceOf(LocalePoint.class);
        assertThat(localePoint.getAddress()).isNotNull().isEqualTo("rua leblon");
    }

}
