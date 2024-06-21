package br.com.fiap.delivery.dto.route;

import lombok.*;

import java.util.stream.Stream;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RouteRequest {

    private String polylineQuality;
    private RouteModifiers routeModifiers;
    private String routingPreference;
    private boolean computeAlternativeRoutes;
    private String travelMode;
    private String languageCode;
    private String units;
    private LocalePoint origin;
    private LocalePoint destination;


    public RouteRequest(String addressOrigin,
                        String addressDestination,
                        boolean bestRouteInRealTime,
                        String typeOfTransport,
                        boolean avoidHighways,
                        boolean avoidTols) {
        typeOfTransport =  TypeTransport.findByTypeBR(typeOfTransport);
        this.polylineQuality = Constants.TypePolyLine.DEFAULT;
        this.routeModifiers = new RouteModifiers(avoidHighways, avoidTols);
        this.routingPreference = defineRoutingPreference(bestRouteInRealTime,typeOfTransport);
        this.computeAlternativeRoutes = false;
        this.travelMode = typeOfTransport;
        this.languageCode = Constants.Lang.PORTUGUESE;
        this.units = Constants.Units.METRIC;
        this.origin = new LocalePoint(addressOrigin);
        this.destination = new LocalePoint(addressDestination);
        this.routeModifiers = new RouteModifiers(avoidHighways, avoidTols);
    }

    protected String defineRoutingPreference (boolean bestRouteInRealTime, String typeOfTransport) {
        if (!typeOfTransport.equals("drive"))
            return Constants.TypeRoute.UNSPECIFIED;
        return bestRouteInRealTime ? Constants.TypeRoute.OPTIMAL : Constants.TypeRoute.DEFAULT;
    }

    @Getter
    public enum TypeTransport {
        CAR("carro", "drive"),
        MOTORCYCLE("moto", "drive"),
        TRUCK("caminhão", "drive"),
        BICKE("bicicleta", "bicycle"),
        FOOT("andando", "walk");

        private final String ptBR;
        private final String enUS;

        TypeTransport(String ptBR, String enUS) {
            this.ptBR = ptBR;
            this.enUS = enUS;
        }

        public static String findByTypeBR(String ptBR) {
            return findByType(ptBR).getEnUS();
        }

        public static TypeTransport findByType(String ptBR) {
            return Stream.of(TypeTransport.values())
                    .filter(type -> type.getPtBR().equals(ptBR))
                    .findFirst()
                    .orElse(TypeTransport.CAR);
        }

    }


}
