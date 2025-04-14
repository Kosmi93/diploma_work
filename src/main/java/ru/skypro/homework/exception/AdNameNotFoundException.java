package ru.skypro.homework.exception;

public class AdNameNotFoundException extends RuntimeException {
    public AdNameNotFoundException(String adName) {
        super("Ad not found with AdName: " + adName);
    }

}
