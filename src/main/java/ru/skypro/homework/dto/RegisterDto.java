package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @Size(min = 5, max = 20, message = "Логин должен содержать от 5 до 20 символов")
    @NotBlank(message = "Логин обязателен для заполнения")
    private String username;
    @Size(min = 5, max = 20, message = "Пароль должен содержать от 5 до 20 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String password;
    @NotBlank(message = "Имя обязательно для заполнения")
    private String firstName;
    @NotBlank(message = "Фамилия обязательна для заполнения")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @NotBlank(message = "Телефон обязателен для заполнения")
    private String phone;
    @NotNull(message = "Роль обязательна для заполнения")
    private RoleDto role;
}
