package com.group4.validation;

import com.group4.entities.Cart;
import com.group4.validation.exception.ValidationException;

/**
 * @author javid
 * Created on 2/3/2022
 */
public class CartValidation {

    private CartValidation() {
    }

    private static class Singleton {
        private static final CartValidation INSTANCE = new CartValidation();
    }

    public static CartValidation getInstance() {
        return Singleton.INSTANCE;
    }

    public void validateNotEmpty(Cart cart) {
        if (cart.getProducts().isEmpty()) {
            throw new ValidationException("Cart is empty.");
        }
    }
}
