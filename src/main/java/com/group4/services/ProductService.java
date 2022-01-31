package com.group4.services;

import com.group4.entities.Category;
import com.group4.entities.Product;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface ProductService extends Service<Product, Integer> {

    void update(Product product);

    List<Product> findAll();

    List<Product> findAllByCategory(Category category);
}
