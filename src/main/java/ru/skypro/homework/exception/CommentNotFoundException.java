package ru.skypro.homework.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String commentIsNotFound) {super("Comment not found with username: " + commentIsNotFound);
    }
}
