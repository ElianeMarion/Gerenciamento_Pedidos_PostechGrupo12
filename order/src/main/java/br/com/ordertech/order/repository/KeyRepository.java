package br.com.ordertech.order.repository;


import br.com.ordertech.order.model.Key;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends MongoRepository<Key, Integer> {
    Key findByChave(String key);
}
