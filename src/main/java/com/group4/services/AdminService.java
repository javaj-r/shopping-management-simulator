package com.group4.services;

import com.group4.entities.Admin;
import com.group4.entities.Product;

import java.util.List;

/**
 * @author javid
 * Created on 1/31/2022
 */
public interface AdminService extends Service<Admin, Integer> {

    Admin findByUsernameAndPassword(Admin admin);

    Integer saveProduct(Product product);

    void updateProduct(Product product);

    List<Product> findAllProduct();
}
