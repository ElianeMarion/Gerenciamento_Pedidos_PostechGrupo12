package br.com.fiap.delivery.model;

import br.com.fiap.delivery.enums.CourierStatus;
import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CourierTest {

    @Test
    void testInstance() {
        Courier courier = new Courier();
        courier.setCourierID("fghjk123");
        courier.setCourierName("Joãozinho");
        courier.setStatus(CourierStatus.FREE.getDesc());
        courier.setLastDelivery(LocalDateTime.of(2023,10,1,11,23,59));

        assertThat(courier).isNotNull().isInstanceOf(Courier.class);
        assertThat(courier.getCourierID()).isNotNull().isEqualTo("fghjk123");
        assertThat(courier.getCourierName()).isNotNull().isEqualTo("Joãozinho");
        assertThat(courier.getStatus()).isNotNull().isEqualTo(CourierStatus.FREE.getDesc());
        assertThat(courier.getLastDelivery()).isNotNull().isEqualTo(LocalDateTime.of(2023,10,1,11,23,59));
    }

    @Test
    void testConstructor() {
        Courier courier = new Courier("Maria Josefina", CourierStatus.FREE.getDesc());
        assertThat(courier).isNotNull().isInstanceOf(Courier.class);
        assertThat(courier.getCourierName()).isNotNull().isEqualTo("Maria Josefina");
        assertThat(courier.getStatus()).isNotNull().isEqualTo(CourierStatus.FREE.getDesc());
    }

    @Test
    void testConstructorWithCourierDto() {
        Courier courier = new Courier(Util.Objects.buildCourier());
        assertThat(courier).isNotNull().isInstanceOf(Courier.class);
    }

}
