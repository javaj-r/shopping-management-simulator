package com.group4.repositories;

import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */
public interface CrudRepository<T, I> {

    List<T> findAll();

    T findById(I id);

    I save(T entity);

    void update(T entity);

    void deleteById(I id);

}
