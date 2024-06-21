package br.com.fiap.delivery.dto.route;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalizedValueTest {

    @Test
    void testInstance() {
        LocalizedValue localizedValue = new LocalizedValue();
        localizedValue.setText("10 km");
        assertThat(localizedValue).isNotNull().isInstanceOf(LocalizedValue.class);
        assertThat(localizedValue.getText()).isNotNull().isEqualTo("10 km");
    }

}
