package com.example.torderproject.modules.payment.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
