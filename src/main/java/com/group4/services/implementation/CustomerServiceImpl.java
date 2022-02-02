package com.group4.services.implementation;

import com.group4.entities.Customer;
import com.group4.repositories.CustomerRepository;
import com.group4.services.CustomerService;
import com.group4.validation.UserValidation;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
        Customer fetchedCustomer = repository.findByUsernameAndPassword(customer);
        UserValidation.getInstance().validateToLogin(fetchedCustomer);
        return fetchedCustomer;
    }

    @Override
    public Integer save(Customer entity) {
        return repository.save(entity);
    }
}
