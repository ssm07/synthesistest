package com.restwebservice.synethesis.services;

import com.restwebservice.synethesis.beans.Payment;
import com.restwebservice.synethesis.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements  PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
      return  paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPayment(Integer paymentId) {
     return paymentRepository.findById(paymentId);
    }
}
