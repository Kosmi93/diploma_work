package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @Size(min = 5, max = 20, message = "Логин должен содержать от 5 до 20 символов")
    @NotBlank(message = "Логин обязателен для заполнения")
    private String username;
    @Size(min = 5, max = 20, message = "Пароль должен содержать от 5 до 20 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String password;
}
