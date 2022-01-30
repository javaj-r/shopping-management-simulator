package com.group4.repositories;

import com.group4.entities.Admin;

/**
 * @author javid
 * Created on 1/30/2022
 */
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Admin findByUsernameAndPassword(Admin admin);
}
