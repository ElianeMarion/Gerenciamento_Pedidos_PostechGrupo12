package br.com.fiap.delivery.dto.route;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {

    private List<Route> routes;


}
