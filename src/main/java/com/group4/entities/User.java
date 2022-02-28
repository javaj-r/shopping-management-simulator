package com.group4.entities;

import com.group4.entities.base.BaseEntity;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class User extends BaseEntity<Integer> {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
