package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SetPasswordDto {
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String currentPassword;

    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String newPassword;
}
