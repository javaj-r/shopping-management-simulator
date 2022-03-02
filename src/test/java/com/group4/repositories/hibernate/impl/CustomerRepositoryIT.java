package com.group4.repositories.hibernate.impl;

import com.group4.connection.HibernateSessionFactory;
import com.group4.entities.Customer;
import com.group4.repositories.CustomerRepository;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryIT {

    SessionFactory sessionFactory;
    CustomerRepository customerRepository;

    Customer customer1;
    Customer customer2;

    @BeforeEach
    void setUp() {
        sessionFactory = HibernateSessionFactory.getInstance();
        customerRepository = new CustomerRepositoryImpl(sessionFactory);

        customer1 = new Customer();
        customer1.setFirstName("f1");
        customer1.setLastName("l1");
        customer1.setUsername("u1");
        customer1.setPassword("p1");
        customer1.setEmail("c1@gmail.com");
        customer1.setNationalCode(1111L);
        customer1.setPhoneNumber(111111L);

        customer2 = new Customer();
        customer2.setFirstName("f2");
        customer2.setLastName("l2");
        customer2.setUsername("u2");
        customer2.setPassword("p2");
        customer2.setEmail("c2@gmail.com");
        customer2.setNationalCode(2222L);
        customer2.setPhoneNumber(222222L);
    }

    @AfterEach
    void tearDown() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.createQuery("DELETE FROM Customer")
                        .executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Test
    void findAll() {
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        var customers = customerRepository.findAll();

        assertEquals(customers.size(), 2);
    }

    @Test
    void findById() {
        var id = customerRepository.save(customer1);

        var actual = customerRepository.findById(id);

        assertAll(
                () -> assertEquals(customer1.getFirstName(), actual.getFirstName()),
                () -> assertEquals(customer1.getLastName(), actual.getLastName()),
                () -> assertEquals(customer1.getUsername(), actual.getUsername()),
                () -> assertEquals(customer1.getPassword(), actual.getPassword()),
                () -> assertEquals(customer1.getEmail(), actual.getEmail()),
                () -> assertEquals(customer1.getNationalCode(), actual.getNationalCode()),
                () -> assertEquals(customer1.getPhoneNumber(), actual.getPhoneNumber())
        );
    }

    @Test
    void save() {
        customerRepository.save(customer1);

        customerRepository.findById(customer1.getId());

        assertFalse(customer1.isNew());
    }

    @Test
    void update() {
        var id = customerRepository.save(customer1);
        customer2.setId(id);

        customerRepository.update(customer2);

        var actual = customerRepository.findById(id);

        assertAll(
                () -> assertEquals(customer2.getFirstName(), actual.getFirstName()),
                () -> assertEquals(customer2.getLastName(), actual.getLastName()),
                () -> assertEquals(customer2.getUsername(), actual.getUsername()),
                () -> assertEquals(customer2.getPassword(), actual.getPassword()),
                () -> assertEquals(customer2.getEmail(), actual.getEmail()),
                () -> assertEquals(customer2.getNationalCode(), actual.getNationalCode()),
                () -> assertEquals(customer2.getPhoneNumber(), actual.getPhoneNumber())
        );
    }

    @Test
    void deleteById() {
        customerRepository.save(customer1);

        customerRepository.deleteById(customer1.getId());

        assertNull(customerRepository.findById(customer1.getId()));
    }

    @Test
    void findByUsernameAndPassword() {
        var customer = new Customer();
        customer.setUsername(customer1.getUsername());
        customer.setPassword(customer1.getPassword());
        customerRepository.save(customer1);

        var actual = customerRepository.findByUsernameAndPassword(customer);

        assertEquals(customer1.getId(), actual.getId());
    }
}