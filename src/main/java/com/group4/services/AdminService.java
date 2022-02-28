package com.group4.services;

import com.group4.entities.Admin;
import com.group4.services.base.BaseService;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface AdminService extends BaseService<Admin, Integer> {

    Admin findByUsernameAndPassword(Admin admin);
}
