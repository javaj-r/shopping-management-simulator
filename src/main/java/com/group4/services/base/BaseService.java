package com.group4.services.base;

import com.group4.entities.base.BaseEntity;

import java.io.Serializable;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    ID save(T entity);
}
