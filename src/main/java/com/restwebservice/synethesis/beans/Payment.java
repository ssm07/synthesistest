package com.restwebservice.synethesis.beans;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * <p> Simple POJO  to store  payment details
 * </p>
 *
 * @Author Saurabh Moghe
 * */
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    @NotNull
    private String paymentAddress;
    @NotNull
    @Size(min = 13, max = 16, message = "Not a valid credit card number.")

    private String creditCard;
    @NotNull
    @Size(min = 3, max = 4, message = " Not a valid CVV")

    private String cvv;

    @NotNull
    @Future(message = "expiry date should be future date")
    private Date expiryDate;



    public Payment() {
    }


    public Payment( Integer paymentId, String paymentAddress,  String creditCard,   String cvv, Date expiryDate) {
        this.paymentId=paymentId;
        this.paymentAddress = paymentAddress;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }



    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
