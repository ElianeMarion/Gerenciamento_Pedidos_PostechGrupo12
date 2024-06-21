package br.com.fiap.delivery.dto.route;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PolyLineTest {

    @Test
    void testInstance() {
        PolyLine polyLine = new PolyLine();
        polyLine.setEncodedPolyline("polyline");
        assertThat(polyLine).isNotNull().isInstanceOf(PolyLine.class);
        assertThat(polyLine.getEncodedPolyline()).isNotNull().isEqualTo("polyline");
    }

}
