package com.group4.services;

import com.group4.entities.Category;
import com.group4.services.base.BaseService;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface CategoryService extends BaseService<Category, Integer> {

    List<Category> findAll();

    List<Category> findAllChildes(Category category);
}
