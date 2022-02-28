package com.group4.repositories;

import com.group4.entities.Category;
import com.group4.entities.Product;
import com.group4.repositories.base.CrudRepository;

import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAllByCategory(Category category);
}
