package com.group4.services.implementation;

import com.group4.entities.Cart;
import com.group4.entities.Category;
import com.group4.entities.Customer;
import com.group4.entities.Product;
import com.group4.repositories.CustomerRepository;
import com.group4.services.CartService;
import com.group4.services.CategoryService;
import com.group4.services.CustomerService;
import com.group4.services.ProductService;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CartService cartService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public CustomerServiceImpl(CustomerRepository repository, CartService cartService, ProductService productService, CategoryService categoryService) {
        this.repository = repository;
        this.cartService = cartService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public Customer findByUsernameAndPassword(Customer customer) {
        return repository.findByUsernameAndPassword(customer);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryService.findAll();
    }

    @Override
    public List<Product> findAllProductsByCategory(Category category) {
        return productService.findAllByCategory(category);
    }

    @Override
    public Integer saveCart(Cart cart) {
        return cartService.save(cart);
    }

    @Override
    public Integer save(Customer entity) {
        return repository.save(entity);
    }
}
