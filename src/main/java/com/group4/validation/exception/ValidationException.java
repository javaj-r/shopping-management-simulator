package com.group4.validation.exception;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class ValidationException extends RuntimeException{

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
