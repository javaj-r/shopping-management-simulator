package com.group4.repositories.hibernate.impl;

import com.group4.entities.Customer;
import com.group4.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Customer", Customer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Customer findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Customer.class, id);
        }
    }

    @Override
    public Integer save(Customer entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return entity.getId();
    }

    @Override
    public void update(Customer entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.createQuery("DELETE FROM Customer a WHERE a.id = :ID")
                        .setParameter("ID", id)
                        .executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Customer WHERE username = :Username AND password = :Password", Customer.class)
                    .setParameter("Username", customer.getUsername())
                    .setParameter("Password", customer.getPassword())
                    .getSingleResult();
        } catch (NoResultException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
