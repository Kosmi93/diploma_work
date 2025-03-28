package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SetPasswordDto {
    @Size(min = 5, max = 20, message = "Пароль должен содержать от 5 до 20 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String currentPassword;

    @Size(min = 5, max = 20, message = "Пароль должен содержать от 5 до 20 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String newPassword;
}
