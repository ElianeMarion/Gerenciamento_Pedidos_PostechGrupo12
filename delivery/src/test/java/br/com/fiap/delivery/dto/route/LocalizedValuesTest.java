package br.com.fiap.delivery.dto.route;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalizedValuesTest {

    @Test
    void testInstance() {
        LocalizedValues localizedValues = new LocalizedValues();
        localizedValues.setDistance(new LocalizedValue("10 km"));
        localizedValues.setDuration(new LocalizedValue("12 min"));
        localizedValues.setStaticDuration(new LocalizedValue("10 min"));
        assertThat(localizedValues).isNotNull().isInstanceOf(LocalizedValues.class);
        assertThat(localizedValues.getDuration()).isNotNull().isInstanceOf(LocalizedValue.class);
        assertThat(localizedValues.getStaticDuration()).isNotNull().isInstanceOf(LocalizedValue.class);
        assertThat(localizedValues.getDistance()).isNotNull().isInstanceOf(LocalizedValue.class);
    }

}
