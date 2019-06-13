package com.restwebservice.synethesis.controllers;

import com.restwebservice.synethesis.beans.Payment;
import com.restwebservice.synethesis.exception.PaymentNotFoundException;
import com.restwebservice.synethesis.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 *  <p>
 *       PaymentController provides REST API to create and fetch payments.
 *
 *  </p>
 *
 *  @Author Saurabh Moghe
 * */
@RestController
public class PaymentController {

     @Autowired
    PaymentService paymentService;
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping(path = "/payment/{paymentId}")
    public @ResponseBody Payment  getPayment(@PathVariable Integer paymentId){
        log.info("PaymentController # getPayment method invoked ");
       Optional<Payment> payment= paymentService.getPayment(paymentId);
       if(!payment.isPresent())
           throw new PaymentNotFoundException("payment id " +paymentId+" does not exist");
        return  payment.get();
    }

    @PostMapping (path = "/payment")
    public  @ResponseBody  ResponseEntity<Object> createPayment( @Valid @RequestBody Payment payment){
          //credit card no will mask in log file
         log.info("credit card " +payment.getCreditCard());
         paymentService.createPayment(payment);
         URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getPaymentId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
