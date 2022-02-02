package com.group4.services.impl;

import com.group4.entities.Admin;
import com.group4.repositories.AdminRepository;
import com.group4.services.AdminService;
import com.group4.validation.UserValidation;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;

    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin findByUsernameAndPassword(Admin admin) {
        Admin fetchedAdmin = repository.findByUsernameAndPassword(admin);
        UserValidation.getInstance().validateToLogin(fetchedAdmin);
        return fetchedAdmin;
    }

    @Override
    public Integer save(Admin entity) {
        return repository.save(entity);
    }
}
