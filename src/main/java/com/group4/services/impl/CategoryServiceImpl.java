package com.group4.services.impl;

import com.group4.entities.Category;
import com.group4.repositories.CategoryRepository;
import com.group4.services.CategoryService;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> findAllChildes(Category category) {
        return repository.findAllChildes(category);
    }

    @Override
    public Integer save(Category entity) {
        return repository.save(entity);
    }
}
