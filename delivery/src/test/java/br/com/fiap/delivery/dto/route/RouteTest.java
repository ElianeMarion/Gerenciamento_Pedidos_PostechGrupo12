package br.com.fiap.delivery.dto.route;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RouteTest {

    @Test
    void testInstance() {
        Route route = new Route();
        route.setLocalizedValues(new LocalizedValues());
        route.setWarnings(List.of(""));
        route.setDistanceMeters(1000);
        route.setPolyline(new PolyLine("PolyLine"));
        route.setDuration("120s");
        assertThat(route).isNotNull().isInstanceOf(Route.class);
    }

    @Test
    void instanceAllArgsConstructor() {
        Route route = new Route(1000, "10 s",
                new PolyLine("PolyLine"),
                List.of("trajeto com 2 pedágios"), new LocalizedValues());
        assertThat(route).isNotNull().isInstanceOf(Route.class);
    }

}
