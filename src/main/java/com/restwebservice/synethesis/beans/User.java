package com.restwebservice.synethesis.beans;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * <p> Simple POJO  to store  payment details
 * </p>
 *
 * @Author Saurabh Moghe
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotNull
    @Size(min = 2, message = " Name should be at least 2 character long")
    private String name;

    @NotNull
    @Email(message = "Invalid Email Id")
    private String emailAddress;
    @NotNull(message = "address can not be null")
    private String address;


    public User(Integer userId, String name, String emailAddress, String address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
