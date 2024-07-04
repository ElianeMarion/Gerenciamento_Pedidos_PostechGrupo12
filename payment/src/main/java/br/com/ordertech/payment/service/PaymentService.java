package br.com.ordertech.payment.service;

import br.com.ordertech.payment.config.Mapper;
import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.exceptions.PaymentNotFoundException;
import br.com.ordertech.payment.model.Payment;
import br.com.ordertech.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventGateway paymentEventGateway;
    private final Mapper mapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentEventGateway paymentEventGateway, Mapper mapper) {
        this.paymentRepository = paymentRepository;
        this.paymentEventGateway = paymentEventGateway;
        this.mapper = mapper;
    }

    public PaymentDto savePayment(PaymentDto paymentDto){
        Payment payment = paymentDto.toPayment(paymentDto);
        payment.setDatePayment(LocalDateTime.now());
        payment = paymentRepository.save(payment);
        paymentDto = mapper.map(payment, PaymentDto.class);
        paymentEventGateway.sendPaymentCreatedEvent(paymentDto);
        return paymentDto;
    }

    public Payment findById(UUID id){
        return paymentRepository.findById(id)
                .orElseThrow(()-> new PaymentNotFoundException("Pagamento não encontrado."));
    }

    public List<Payment> listAllPayment(){
        return paymentRepository.findAll();
    }
}
