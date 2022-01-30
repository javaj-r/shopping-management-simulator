package com.group4.entities;

/**
 * @author javid
 * Created on 1/30/2022
 */
public class Category extends BaseEntity {

    private Category parentCategory;

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
