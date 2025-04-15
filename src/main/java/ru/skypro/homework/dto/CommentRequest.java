package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentRequest {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

