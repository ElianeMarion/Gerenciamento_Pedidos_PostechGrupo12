package br.com.fiap.delivery.service;

import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.model.Courier;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourierService {


    CourierDto saveCourier(CourierDto courierDto);

    Courier findById(String courierID);

    ResponseEntity<List<CourierDto>> findAll();

    ResponseEntity<CourierDto> updateStatus(String courierId, String status);

}
