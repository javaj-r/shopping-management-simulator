package com.group4.context;

import com.group4.connection.PostgresConnection;
import com.group4.console.AdminConsole;
import com.group4.console.CategoryConsole;
import com.group4.console.CustomerConsole;
import com.group4.console.ProductConsole;
import com.group4.repositories.*;
import com.group4.repositories.jdbc.*;
import com.group4.services.*;
import com.group4.services.implementation.*;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class ApplicationContext {

    private ApplicationContext() {
        throw new IllegalStateException("Utility class");
    }

    public static PostgresConnection getPostgresConnection() {
        return PostgresConnection.getInstance();
    }

    public static AdminRepository getAdminRepository() {
        return new AdminRepositoryImpl(getPostgresConnection());
    }

    public static AdminService getAdminService() {
        return new AdminServiceImpl(getAdminRepository());
    }

    public static AdminConsole getAdminConsole() {
        return new AdminConsole(getAdminService());
    }

    public static ProductConsole getProductConsole() {
        return new ProductConsole(getProductService());
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl(getProductRepository());
    }

    private static ProductRepository getProductRepository() {
        return new ProductRepositoryImpl(getPostgresConnection());
    }

    public static CategoryConsole getCategoryConsole() {
        return new CategoryConsole(getCategoryService());
    }

    public static CategoryService getCategoryService() {
        return new CategoryServiceImpl(getCategoryRepository());
    }

    public static CategoryRepository getCategoryRepository() {
        return new CategoryRepositoryImpl(getPostgresConnection());
    }

    public static CustomerConsole getCustomerConsole() {
        return new CustomerConsole(getCustomerService(), getCartService());
    }

    private static CartService getCartService() {
        return new CartServiceImpl(getCartRepository());
    }

    private static CartRepository getCartRepository() {
        return new CartRepositoryImpl(getPostgresConnection());
    }

    private static CustomerService getCustomerService() {
        return new CustomerServiceImpl(getCustomerRepository());
    }

    private static CustomerRepository getCustomerRepository() {
        return new CustomerRepositoryImpl(getPostgresConnection());
    }

}
