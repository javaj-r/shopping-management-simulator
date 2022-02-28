package com.group4.services.base;

import com.group4.entities.base.BaseEntity;
import com.group4.repositories.base.CrudRepository;

import java.io.Serializable;

public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends CrudRepository<T, ID>> implements BaseService<T, ID> {

    protected final R repository;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }
}
