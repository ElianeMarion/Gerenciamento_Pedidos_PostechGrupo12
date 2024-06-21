package br.com.fiap.delivery.dto.route;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RouteModifiers {
    private boolean avoidFerries;
    private boolean avoidHighways;
    private boolean avoidIndoor;
    private boolean avoidTolls;

    public RouteModifiers(boolean avoidHighways, boolean avoidTolls) {
        this.avoidHighways = avoidHighways;
        this.avoidTolls = avoidTolls;
    }
}
