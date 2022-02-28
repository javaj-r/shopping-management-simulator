package com.group4.services.base;

import com.group4.entities.base.BaseEntity;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface BaseService<T extends BaseEntity<I>, I> {

    I save(T entity);
}
