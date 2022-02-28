package com.group4.services;

import com.group4.entities.Customer;
import com.group4.services.base.BaseService;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface CustomerService extends BaseService<Customer, Integer> {

    Customer findByUsernameAndPassword(Customer customer);
}
