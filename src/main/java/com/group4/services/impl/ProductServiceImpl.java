package com.group4.services.impl;

import com.group4.entities.Category;
import com.group4.entities.Product;
import com.group4.repositories.ProductRepository;
import com.group4.services.ProductService;
import com.group4.services.base.BaseServiceImpl;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class ProductServiceImpl extends BaseServiceImpl<Product, Integer, ProductRepository> implements ProductService {

    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public Integer save(Product entity) {
        return repository.save(entity);
    }
}
