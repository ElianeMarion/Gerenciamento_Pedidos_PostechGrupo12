package br.com.ordertech.payment.infra;

import br.com.ordertech.payment.dto.PaymentDto;
import br.com.ordertech.payment.enums.StatusPaymentEnum;
import br.com.ordertech.payment.repository.PaymentRepository;
import br.com.ordertech.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentUpdatedEventListner implements Consumer<PaymentDto> {


    private final PaymentService paymentService;

    @Override
    public void accept(PaymentDto paymentDto) {

        log.info("Pagamento gerado pelo pedido " + paymentDto.getPaymentId() );
        paymentDto.setStatus(StatusPaymentEnum.PROCESSING);
        paymentService.updatePayment(paymentDto);

    }
}
