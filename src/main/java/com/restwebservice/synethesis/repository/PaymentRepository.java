package com.restwebservice.synethesis.repository;

import com.restwebservice.synethesis.beans.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
