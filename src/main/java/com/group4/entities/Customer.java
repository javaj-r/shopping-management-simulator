package com.group4.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class Customer extends User {

    private String firstName;
    private String lastName;
    private Long nationalCode;
    private String email;
    private Long phoneNumber;
    private Cart cart;
    private List<Cart> previousOrders = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Cart> getPreviousOrders() {
        return previousOrders;
    }

    public void setPreviousOrders(List<Cart> previousOrders) {
        this.previousOrders = previousOrders;
    }
}
