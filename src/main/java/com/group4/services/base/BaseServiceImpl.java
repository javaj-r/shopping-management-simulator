package com.group4.services.base;

import com.group4.entities.base.BaseEntity;
import com.group4.repositories.base.CrudRepository;

public abstract class BaseServiceImpl<T extends BaseEntity<I>, I, R extends CrudRepository<T, I>> implements BaseService<T, I> {

    protected final R repository;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }
}
