package ru.skypro.homework.exception;

public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException(String message) {
        super("Ad not found with username: " + message);
    }
}
