package com.group4.services.implementation;

import com.group4.entities.Category;
import com.group4.entities.Product;
import com.group4.repositories.ProductRepository;
import com.group4.services.ProductService;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
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
