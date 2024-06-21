package br.com.fiap.delivery.dto;

import br.com.fiap.delivery.model.CourierView;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CourierDto {

    private String courierID;
    @NotBlank(groups = {CourierView.Save.class})
    @Length(min = 2, groups = {CourierView.Save.class})
    private String courierName;

    @NotBlank(groups = {CourierView.Update.class})
    private String status;

    private LocalDateTime lastDelivery;

    public CourierDto(String name) {
        this.courierName = name;
    }


}
