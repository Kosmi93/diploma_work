package ru.skypro.homework.exception;

import java.io.IOException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User not found with username: " + username);
    }
}