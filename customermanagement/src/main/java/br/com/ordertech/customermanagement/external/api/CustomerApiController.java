package br.com.ordertech.customermanagement.external.api;

import br.com.ordertech.customermanagement.entity.customer.CustomerEntity;
import br.com.ordertech.customermanagement.external.db.DataBaseJpa;
import br.com.ordertech.customermanagement.external.dto.CustomerDto;
import br.com.ordertech.customermanagement.infraestructure.controller.CustomerController;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerApiController {
    private final DataBaseJpa dataBaseJpa;
    private final CustomerController customerController;

    public CustomerApiController(DataBaseJpa dataBaseJpa,
                                 CustomerController customerController) {
        this.dataBaseJpa = dataBaseJpa;
        this.customerController = customerController;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CustomerDto> register(@RequestBody CustomerDto customerDto) {
        CustomerEntity customerEntity = customerController.register(customerDto.toRecord(), dataBaseJpa);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDto(customerEntity));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long customerId) {
        CustomerEntity customerEntity = customerController.findById(customerId, dataBaseJpa);
        return ResponseEntity.ok(new CustomerDto(customerEntity));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<CustomerDto>> findByName(@PathVariable String name) {
        List<CustomerEntity> customerEntities = customerController.findByName(name, dataBaseJpa);
        return ResponseEntity.ok(customerEntities.stream().map(CustomerDto::new).toList());
    }

    @Transactional
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> update(
            @PathVariable Long customerId,
            @RequestBody @Valid CustomerDto customerDto) {
        CustomerEntity customerEntity = customerController.
                update(customerId, customerDto.toRecord(), dataBaseJpa);
        return ResponseEntity.ok(new CustomerDto(customerEntity));
    }

    @Transactional
    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDto> delete(@PathVariable Long customerId) {
        customerController.delete(customerId, dataBaseJpa);
        return ResponseEntity.noContent().build();
    }

}
