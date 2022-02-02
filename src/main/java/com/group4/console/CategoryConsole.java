package com.group4.console;

import com.group4.Application;
import com.group4.entities.Category;
import com.group4.services.CategoryService;
import com.group4.util.Screen;

import java.util.*;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class CategoryConsole {

    private final CategoryService categoryService;

    public CategoryConsole(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category selectOrAdd(String message) {
        List<Category> categories = categoryService.findAll();
        List<String> items = new ArrayList<>();
        items.add("Add new category");
        items.addAll(categories.stream()
                .map(Category::getName)
                .toList());

        int choice = Screen.showMenu(message, "Cancel", items);
        if (choice == 0) {
            return new Category();
        }

        return choice == 1 ? createCategory() : categories.get(choice - 2);
    }


    public Category select(String message) {
        List<Category> categories = categoryService.findAll();
        List<String> items = new ArrayList<>();
        items.addAll(categories.stream()
                .map(Category::getName)
                .toList());

        int choice = Screen.showMenu(message, "Cancel", items);
        if (choice == 0) {
            return new Category();
        }

        return categories.get(choice - 1);
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName(Screen.getString("Enter product name: "));
        category.setParentCategory(selectOrAdd("Select parent category from list: "));
        if (Application.confirmMenu("Save category") > 0) {
            category.setId(categoryService.save(category));
        }
        return category;
    }
}
