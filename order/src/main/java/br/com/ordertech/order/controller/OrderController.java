package br.com.ordertech.order.controller;

import br.com.ordertech.order.dto.CustomerDto;
import br.com.ordertech.order.model.Order;
import br.com.ordertech.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> listAll(){

        return ResponseEntity.ok(this.orderService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        try{
            log.info("Request -> {}", order);
            Order newOrder = orderService.saveOrder(order);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        }
        catch (NoSuchElementException e){
            log.error("Produto não disponível ", e);
            return new ResponseEntity<>("Produto não disponível", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/customer")
    public ResponseEntity<CustomerDto> getCustomerByOrderId(@PathVariable Integer id) {
        Integer customerId = 1;
        CustomerDto customer = orderService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}/{statusCode}")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id,
                                              @PathVariable Integer statusCode){


        var order = orderService.updateStatus(id, statusCode);
        return ResponseEntity.ok(order);
    }

    
}
