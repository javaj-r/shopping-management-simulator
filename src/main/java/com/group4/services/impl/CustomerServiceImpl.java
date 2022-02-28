package com.group4.services.impl;

import com.group4.entities.Customer;
import com.group4.repositories.CustomerRepository;
import com.group4.repositories.base.CrudRepositoryImpl;
import com.group4.services.CustomerService;
import com.group4.services.base.BaseServiceImpl;
import com.group4.validation.UserValidation;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer, CustomerRepository> implements CustomerService {

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
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
