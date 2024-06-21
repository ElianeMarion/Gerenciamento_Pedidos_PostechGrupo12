package br.com.fiap.delivery.repository;

import br.com.fiap.delivery.model.Courier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourierRepository extends MongoRepository<Courier, String> {
}
