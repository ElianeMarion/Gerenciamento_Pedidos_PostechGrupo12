package br.com.fiap.delivery.dto.route;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RouteResponseTest {

    @Test
    void testInstance() {
        RouteResponse resp = Util.Objects.buildRouteResponse();
        assertThat(resp).isNotNull().isInstanceOf(RouteResponse.class);
        assertThat(resp.getRoutes()).isNotNull().asList().element(0).isInstanceOf(Route.class);
    }

    @Test
    void testInstanceNoArgsConstructor() {
        RouteResponse resp = new RouteResponse();
        resp.setRoutes(List.of(new Route()));
        assertThat(resp).isNotNull().isInstanceOf(RouteResponse.class);
        assertThat(resp.getRoutes()).isNotNull().asList().element(0).isInstanceOf(Route.class);
    }

}
