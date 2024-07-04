package br.com.ordertech.payment.controller;

import br.com.ordertech.payment.config.Mapper;
import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.model.Payment;
import br.com.ordertech.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;

    }

    @GetMapping
    public ResponseEntity<List<Payment>> listAll(){
        return ResponseEntity.ok(paymentService.listAllPayment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable UUID id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> savePayment(@RequestBody PaymentDto paymentDto){
        paymentDto = paymentService.savePayment(paymentDto);

        return ResponseEntity.ok(paymentDto);
    }


}
