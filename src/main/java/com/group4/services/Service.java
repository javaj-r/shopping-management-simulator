package com.group4.services;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface Service<T, I> {

    I save(T entity);
}
