package br.com.ordertech.order.repository;


import br.com.ordertech.order.model.Pix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends MongoRepository<Pix, Integer> {
    Pix findByIdentifier(String identifier);
}
