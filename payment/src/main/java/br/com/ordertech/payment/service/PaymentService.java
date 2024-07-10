package br.com.ordertech.payment.service;

import br.com.ordertech.payment.config.Mapper;
import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.enums.StatusPaymentEnum;
import br.com.ordertech.payment.exceptions.PaymentNotFoundException;
import br.com.ordertech.payment.model.Payment;
import br.com.ordertech.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
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
        Optional<Payment> paymentFounded = paymentRepository.findById(paymentDto.getPaymentId());
        if(paymentFounded.isPresent()){
            payment = paymentFounded.get();
            payment.setDatePayment(LocalDateTime.now());
            payment.setStatus(StatusPaymentEnum.PAYMENT_COMPLETED);
            payment = paymentRepository.save(payment);
            paymentDto = mapper.map(payment, PaymentDto.class);
            log.info("Pagamento efetivado " + paymentDto.getPaymentId() );
        }
        else {
            log.info("Pagamento gerado pelo pedido " + paymentDto.getPaymentId() );
            paymentDto.setStatus(StatusPaymentEnum.PROCESSING);
            payment = paymentRepository.save(payment);
            paymentDto = mapper.map(payment, PaymentDto.class);
        }

        paymentEventGateway.sendPaymentCreatedEvent(paymentDto);
        return paymentDto;
    }

    public PaymentDto updatePayment(PaymentDto paymentDto){
        Payment payment = paymentDto.toPayment(paymentDto);
        payment = paymentRepository.save(payment);
        paymentDto = mapper.map(payment, PaymentDto.class);
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
