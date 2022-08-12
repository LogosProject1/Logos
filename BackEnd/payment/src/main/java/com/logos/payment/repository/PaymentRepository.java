package com.logos.payment.repository;

import com.logos.payment.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,String> {
    Page<Payment> findByUserEmailOrderByStartTimeDesc(String email, Pageable pageable);
}
