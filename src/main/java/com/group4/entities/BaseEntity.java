package com.group4.entities;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class BaseEntity {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }
}
