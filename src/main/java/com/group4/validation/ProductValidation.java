package com.group4.validation;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class ProductValidation {

    private ProductValidation() {
    }

    private static class Singleton {
        private static final ProductValidation INSTANCE = new ProductValidation();
    }

    public static ProductValidation getInstance() {
        return Singleton.INSTANCE;
    }
}
