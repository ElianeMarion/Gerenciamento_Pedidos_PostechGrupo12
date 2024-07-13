package br.com.fiap.delivery.repository;

import br.com.fiap.delivery.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {

}
