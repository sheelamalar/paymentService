package com.reservation.paymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservation.paymentService.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
