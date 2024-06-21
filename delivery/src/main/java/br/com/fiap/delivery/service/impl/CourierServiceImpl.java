package br.com.fiap.delivery.service.impl;

import br.com.fiap.delivery.dto.CourierDto;
import br.com.fiap.delivery.enums.CourierStatus;
import br.com.fiap.delivery.exception.DeliveryException;
import br.com.fiap.delivery.model.Courier;
import br.com.fiap.delivery.repository.CourierRepository;
import br.com.fiap.delivery.service.CourierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourierServiceImpl implements CourierService {

    private CourierRepository courierRepository;

    private MongoTemplate mongoTemplate;

    @Autowired
    public CourierServiceImpl(CourierRepository courierRepository,
                              MongoTemplate mongoTemplate) {
        this.courierRepository = courierRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CourierDto saveCourier(CourierDto courierDto) {
        Courier courier = new Courier(courierDto.getCourierName(), CourierStatus.FREE.getDesc());
        courier = courierRepository.save(courier);
        BeanUtils.copyProperties(courier, courierDto);
        return courierDto;
    }

    @Override
    public Courier findById(String courierID) {
        return courierRepository.findById(courierID)
                .orElseThrow(() -> new DeliveryException("Entregador não localizado."));
    }

    @Override
    public ResponseEntity<List<CourierDto>> findAll() {
        try {
            return ResponseEntity.ok(courierRepository.findAll()
                    .stream().map(Courier::buildDto).toList());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<CourierDto> updateStatus(String courierId, String status) {
        Optional<Courier> courierOpt = courierRepository.findById(courierId);
        courierOpt.filter(c -> !c.getStatus().equals(status)).ifPresent(courier -> {
            courier.setStatus(status);
            courierRepository.save(courier);
        });
        return courierOpt
                .map(Courier::buildDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
