package com.group4.services.implementation;

import com.group4.entities.Admin;
import com.group4.entities.Product;
import com.group4.repositories.AdminRepository;
import com.group4.services.AdminService;
import com.group4.services.CategoryService;
import com.group4.services.ProductService;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;
    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminServiceImpl(AdminRepository repository, ProductService productService, CategoryService categoryService) {
        this.repository = repository;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public Admin findByUsernameAndPassword(Admin admin) {
        return repository.findByUsernameAndPassword(admin);
    }

    @Override
    public Integer saveProduct(Product product) {
        return productService.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productService.update(product);
    }

    @Override
    public List<Product> findAllProduct() {
        return productService.findAll();
    }

    @Override
    public Integer save(Admin entity) {
        return repository.save(entity);
    }
}
