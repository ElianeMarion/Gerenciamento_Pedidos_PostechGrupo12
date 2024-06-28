package br.com.ordertech.customermanagement.external.db;

import br.com.ordertech.customermanagement.external.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    List<CustomerModel> findByName(String name);
}
