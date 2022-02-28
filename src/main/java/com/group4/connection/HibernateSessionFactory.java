package com.group4.connection;

import com.group4.entities.*;
import com.group4.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateSessionFactory {

    private static class LazyHolder {
        static final SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(BaseEntity.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Cart.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
