package com.group4.validation;

import com.group4.entities.base.BaseEntity;
import com.group4.entities.User;
import com.group4.validation.exception.ValidationException;

import java.util.List;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class UserValidation {

    private UserValidation() {
    }

    private static class Singleton {
        private static final UserValidation INSTANCE = new UserValidation();
    }

    public static UserValidation getInstance() {
        return Singleton.INSTANCE;
    }

    public void validateToLogin(User fetchedUser) {
        if (fetchedUser == null || fetchedUser.isNew()) {
            throw new ValidationException("Username or Password is incorrect.");
        }
    }

    public void validateToRegister(User newUser, List<User> users) {
        validateNotNullEntity(newUser);
        if (newUser.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty.");
        }
        List<String> userNames = users.stream()
                .map(User::getUsername)
                .filter(s -> s.equalsIgnoreCase(newUser.getUsername()))
                .toList();

        if (!userNames.isEmpty()){
            throw new ValidationException("Username already exists.");
        }
    }

    public void validateNotNullEntity(BaseEntity<Integer> entity) {
        if (entity == null) {
            throw new ValidationException("Entity is null.");
        }
    }
}
