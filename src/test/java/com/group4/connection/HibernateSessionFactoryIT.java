package com.group4.connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernateSessionFactoryIT {

    @Test
    void getInstance() {
        assertNotNull(HibernateSessionFactory.getInstance());
    }
}