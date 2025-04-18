package ru.skypro.homework.exception;

public class AccessUserException extends RuntimeException {
    public AccessUserException(String s) {super("User is not authorized ");
    }
}
