package br.com.fiap.microservice.product_manager.repository;

import br.com.fiap.microservice.product_manager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
