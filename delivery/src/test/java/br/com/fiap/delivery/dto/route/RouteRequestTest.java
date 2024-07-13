package br.com.fiap.delivery.dto.route;

import br.com.fiap.delivery.utils.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RouteRequestTest {

    @Test
    void testInstance() {
        RouteRequest req = Util.Objects.buildRouteRequest();
        assertThat(req).isNotNull().isInstanceOf(RouteRequest.class);
        assertThat(req.getOrigin()).isNotNull().isInstanceOf(LocalePoint.class);
        assertThat(req.getDestination()).isNotNull().isInstanceOf(LocalePoint.class);
        assertThat(req.getRoutingPreference()).isNotNull().isEqualTo("traffic_aware_optimal");
        assertThat(req.getTravelMode()).isNotNull().isEqualTo("drive");
        assertThat(req.getPolylineQuality()).isNotNull().isEqualTo("overview");
    }

    @Test
    void testInstanceSetters() {
        RouteRequest req = new RouteRequest();
        req.setOrigin(new LocalePoint("rua leblon"));
        req.setDestination(new LocalePoint("rua urca"));
        req.setLanguageCode(Constants.Lang.PORTUGUESE);
        req.setComputeAlternativeRoutes(false);
        req.setRouteModifiers(Util.Objects.buildRouteRequest().getRouteModifiers());
        req.setRoutingPreference(Constants.TypeRoute.OPTIMAL);
        req.setPolylineQuality(Constants.TypePolyLine.DEFAULT);
        req.setUnits(Constants.Units.METRIC);
        req.setTravelMode("walk");
        req.defineRoutingPreference(false, "walk");
        assertThat(req).isNotNull().isInstanceOf(RouteRequest.class);
        assertThat(req.getOrigin()).isNotNull().isInstanceOf(LocalePoint.class);
        assertThat(req.getDestination()).isNotNull().isInstanceOf(LocalePoint.class);
        assertThat(req.getRoutingPreference()).isNotNull().isEqualTo(Constants.TypeRoute.OPTIMAL.toString());
        assertThat(req.getTravelMode()).isNotNull().isEqualTo("walk");
        assertThat(req.getPolylineQuality()).isNotNull().isEqualTo("overview");
        assertThat(req.getLanguageCode()).isNotNull().isEqualTo("pt-BR");
        assertThat(req.isComputeAlternativeRoutes()).isFalse();
        assertThat(req.getUnits()).isNotNull().isEqualTo("METRIC");
        assertThat(req.toString()).isNotBlank();
    }


}
