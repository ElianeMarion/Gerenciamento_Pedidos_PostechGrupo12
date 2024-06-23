package br.com.fiap.delivery.dto.route;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteModifiers {
    private boolean avoidFerries;
    private boolean avoidHighways;
    private boolean avoidIndoor;
    private boolean avoidTolls;

    public RouteModifiers(){}

    public RouteModifiers(boolean avoidHighways, boolean avoidTolls) {
        this.avoidHighways = avoidHighways;
        this.avoidTolls = avoidTolls;
    }
}
