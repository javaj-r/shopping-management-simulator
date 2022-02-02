package com.group4.validation;

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
}
