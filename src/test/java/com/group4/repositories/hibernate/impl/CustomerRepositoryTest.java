package com.group4.repositories.hibernate.impl;

import com.group4.entities.Customer;
import com.group4.repositories.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {

    @Mock
    SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Query<Customer> query;

    @Mock
    Transaction transaction;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Captor
    ArgumentCaptor<Customer> customerCaptor;

    @Captor
    ArgumentCaptor<Class<Customer>> customerTypeCaptor;

    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepositoryImpl(sessionFactory);
    }

    @Test
    void findAll() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setId(1);
        customer2.setId(2);
        when(query.list()).thenReturn(List.of(customer1, customer2));
        when(session.createQuery("FROM Customer", Customer.class)).thenReturn(query);
        when(sessionFactory.openSession()).thenReturn(session);

        var actual = customerRepository.findAll();

        verify(sessionFactory).openSession();
        verify(session).createQuery("FROM Customer", Customer.class);
        verify(query).list();
        assertEquals(2, actual.size());
        assertArrayEquals(List.of(customer1, customer2).toArray(), actual.toArray());
    }

    @Test
    void findById() {
        Customer customer = new Customer();
        customer.setId(1);
        when(session.find(Customer.class, 1)).thenReturn(customer);
        when(sessionFactory.openSession()).thenReturn(session);

        var actual= customerRepository.findById(1);

        assertEquals(customer, actual);
        verify(sessionFactory).openSession();
        verify(session).find(customerTypeCaptor.capture(), intCaptor.capture());
        assertEquals(Customer.class, customerTypeCaptor.getValue());
        assertEquals(1, intCaptor.getValue());
    }

    @Test
    void save() {
        Customer customer = new Customer();
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        customerRepository.save(customer);

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(transaction).commit();
        verify(session).save(customerCaptor.capture());
        assertEquals(customer, customerCaptor.getValue());
    }

    @Test
    void update() {
        Customer customer = new Customer();
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        customerRepository.update(customer);

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(transaction).commit();
        verify(session).update(customerCaptor.capture());
        assertEquals(customer, customerCaptor.getValue());
    }

    @Test
    void deleteById() {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), anyInt())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        customerRepository.deleteById(1);

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(transaction).commit();
        verify(session).createQuery(anyString());
        verify(query).setParameter("ID", 1);
        verify(query).executeUpdate();
    }

    @Test
    void findByUsernameAndPassword() {
        Customer customer = new Customer();
        customer.setUsername("f");
        customer.setPassword("l");
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM Customer WHERE username = :Username AND password = :Password", Customer.class)).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(customer);

        var actual = customerRepository.findByUsernameAndPassword(customer);

        assertEquals(customer, actual);
        verify(sessionFactory).openSession();
        verify(session).createQuery(stringCaptor.capture(), customerTypeCaptor.capture());
        assertEquals("FROM Customer WHERE username = :Username AND password = :Password", stringCaptor.getValue());
        assertEquals(Customer.class, customerTypeCaptor.getValue());
    }
}