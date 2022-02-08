package com.group4.services.impl;

import com.group4.entities.Cart;
import com.group4.repositories.CartRepository;
import com.group4.services.CartService;
import com.group4.validation.CartValidation;

/**
 * @author javid
 * Created on 1/31/2022
 */
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    public CartServiceImpl(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer save(Cart entity) {
        CartValidation.getInstance().validateNotEmpty(entity);
        return repository.save(entity);
    }
}
