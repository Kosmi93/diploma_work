package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
    @NotBlank(message = "Логин обязателен для заполнения")
    private String userName;

    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    @NotBlank(message = "Пароль обязателен для заполнения")
    private String password;

    @Size(min = 2, max = 16, message = "Имя должно содержать от 2 до 16 символов")
    @NotBlank(message = "Имя обязательно для заполнения")
    private String firstName;

    @Size(min = 2, max = 16, message = "Фамилия должна содержать от 2 до 16 символов")
    @NotBlank(message = "Фамилия обязательна для заполнения")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @NotBlank(message = "Телефон обязателен для заполнения")
    private String phone;

    @NotNull(message = "Роль обязательна для заполнения")
    private RoleDto role;

}
