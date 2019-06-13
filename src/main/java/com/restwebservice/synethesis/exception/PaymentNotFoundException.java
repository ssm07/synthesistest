package com.restwebservice.synethesis.exception;

public class PaymentNotFoundException extends  RuntimeException {

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
