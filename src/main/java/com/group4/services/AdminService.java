package com.group4.services;

import com.group4.entities.Admin;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface AdminService extends Service<Admin, Integer> {

    Admin findByUsernameAndPassword(Admin admin);
}
