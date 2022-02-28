package com.group4.repositories.base;

import com.group4.connection.PostgresConnection;
import com.group4.entities.base.BaseEntity;

public abstract class CrudRepositoryImpl<T extends BaseEntity<ID>, ID> implements CrudRepository<T, ID> {

    protected final PostgresConnection postgresConnection;

    protected CrudRepositoryImpl(PostgresConnection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }
}
