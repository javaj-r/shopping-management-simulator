package com.group4.entities.base;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class BaseEntity<ID> {

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }
}
