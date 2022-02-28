package com.group4.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author javid
 * Created on 1/30/2022
 */

@Getter
@Setter
@MappedSuperclass
public class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public boolean isNew() {
        return id == null;
    }
}
