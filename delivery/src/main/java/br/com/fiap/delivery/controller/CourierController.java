package br.com.fiap.delivery.controller;


import br.com.fiap.delivery.controller.swagger.CourierControllerSwagger;
import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.model.CourierView;
import br.com.fiap.delivery.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery/courier")
public class CourierController implements CourierControllerSwagger {

    private CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping
    public ResponseEntity<CourierDto> save(@RequestBody @Validated({CourierView.Save.class}) CourierDto courierDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courierService.saveCourier(courierDto));
    }

    @GetMapping
    public ResponseEntity<List<CourierDto>> findAll() {
        return courierService.findAll();
    }

    @PutMapping("/{courierID}")
    public ResponseEntity<CourierDto> updateStatus(@PathVariable String courierID,
                                                   @RequestBody @Validated({CourierView.Update.class}) CourierDto courierDto) {
        return courierService.updateStatus(courierID, courierDto.getStatus());
    }

}
