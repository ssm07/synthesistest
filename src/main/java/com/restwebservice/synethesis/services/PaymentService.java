package com.restwebservice.synethesis.services;

import com.restwebservice.synethesis.beans.Payment;

import java.util.Optional;

public interface PaymentService {

    Payment createPayment(Payment payment);
    Optional<Payment> getPayment(Integer paymentId);
}
