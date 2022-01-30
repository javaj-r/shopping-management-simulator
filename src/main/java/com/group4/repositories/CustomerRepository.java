package com.group4.repositories;

import com.group4.entities.Customer;

/**
 * @author javid
 * Created on 1/30/2022
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

    Customer findByUsernameAndPassword(Customer customer);
}
