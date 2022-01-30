package com.group4.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class Cart {

    private Customer costumer;
    private String address;
    private Long phoneNumber;
    private List<Product> products = new ArrayList<>();
    private boolean done;

    public Customer getCostumer() {
        return costumer;
    }

    public void setCostumer(Customer costumer) {
        this.costumer = costumer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
