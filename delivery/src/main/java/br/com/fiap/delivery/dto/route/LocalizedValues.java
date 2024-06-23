package br.com.fiap.delivery.dto.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedValues {

    private LocalizedValue distance;
    private LocalizedValue duration;
    private LocalizedValue staticDuration;


}
