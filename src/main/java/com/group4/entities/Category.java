package com.group4.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class Category extends BaseEntity {

    private Category parentCategory;
    private List<Product> products = new ArrayList<>();

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
