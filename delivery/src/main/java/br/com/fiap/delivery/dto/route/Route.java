package br.com.fiap.delivery.dto.route;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private Integer distanceMeters;
    private String duration;
    private PolyLine polyline;
    private List<String> warnings;
    private LocalizedValues localizedValues;


}
