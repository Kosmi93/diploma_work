package ru.skypro.homework.exception;

import java.io.IOException;

public class InvalidPasswordException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Current password is not correct :(";
    }
}
