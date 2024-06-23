package br.com.fiap.delivery.model;

import br.com.fiap.delivery.dto.CourierDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Courier {


    @MongoId
    private String courierID;
    private String courierName;
    private String status;
    private LocalDateTime lastDelivery;


    public Courier(String courierName, String status) {
        this.courierName = courierName;
        this.status = status;
    }

    public Courier(CourierDto courierDto) {
        this.courierID = courierDto.getCourierID();
        this.courierName = courierDto.getCourierName();
        this.status = courierDto.getStatus();
        this.lastDelivery = courierDto.getLastDelivery();
    }

    public CourierDto buildDto() {
        CourierDto dto = new CourierDto();
        dto.setCourierID(this.courierID);
        dto.setCourierName(this.courierName);
        dto.setStatus(this.status);
        dto.setLastDelivery(this.lastDelivery);
        return dto;
    }
}
