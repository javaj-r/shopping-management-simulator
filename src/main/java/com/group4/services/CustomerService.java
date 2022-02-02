package com.group4.services;

import com.group4.entities.Customer;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface CustomerService extends Service<Customer, Integer> {

    Customer findByUsernameAndPassword(Customer customer);
}
