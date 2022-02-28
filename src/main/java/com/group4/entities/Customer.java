package com.group4.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */

@Getter
@Setter
@Entity
public class Customer extends User {

    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String lastName;

    private Long nationalCode;

    @Column(length = 100)
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Transient
    private Cart cart;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Cart> previousOrders = new ArrayList<>();

}
