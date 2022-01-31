package com.group4.services;

import com.group4.entities.Cart;
import com.group4.entities.Category;
import com.group4.entities.Customer;
import com.group4.entities.Product;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface CustomerService extends Service<Customer, Integer> {

    Customer findByUsernameAndPassword(Customer customer);

    List<Category> findAllCategories();

    List<Product> findAllProductsByCategory(Category category);

    Integer saveCart(Cart cart);
}
