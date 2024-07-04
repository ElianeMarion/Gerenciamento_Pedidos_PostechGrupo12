package br.com.ordertech.order.repository.impl;

import br.com.ordertech.order.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}
