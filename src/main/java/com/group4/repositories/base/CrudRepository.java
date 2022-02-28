package com.group4.repositories.base;

import com.group4.entities.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */
public interface CrudRepository<T  extends BaseEntity<ID>, ID extends Serializable> {

    List<T> findAll();

    T findById(ID id);

    ID save(T entity);

    void update(T entity);

    void deleteById(ID id);

}
