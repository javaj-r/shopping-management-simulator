package com.group4.entities;

import com.group4.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author javid
 * Created on 1/30/2022
 */

@Getter
@Setter
@MappedSuperclass
public class User extends BaseEntity<Integer> {

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String password;

}
