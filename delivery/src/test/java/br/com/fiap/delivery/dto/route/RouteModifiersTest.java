package br.com.fiap.delivery.dto.route;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RouteModifiersTest {

    @Test
    void testInstance() {
        RouteModifiers routeModifiers = new RouteModifiers();
        routeModifiers.setAvoidFerries(true);
        routeModifiers.setAvoidIndoor(true);
        routeModifiers.setAvoidHighways(true);
        routeModifiers.setAvoidTolls(true);
        assertThat(routeModifiers).isNotNull().isInstanceOf(RouteModifiers.class);
        assertThat(routeModifiers.isAvoidFerries()).isTrue();
        assertThat(routeModifiers.isAvoidHighways()).isTrue();
        assertThat(routeModifiers.isAvoidIndoor()).isTrue();
        assertThat(routeModifiers.isAvoidTolls()).isTrue();
    }

}
