package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class UpdateUserDto {
    @Size(min = 2, max = 16, message = "Имя должно содержать от 2 до 16 символов")
    @NotBlank(message = "Имя обязательно для заполнения")
    private String firstName;

    @Size(min = 2, max = 16, message = "Фамилия должна содержать от 2 до 16 символов")
    @NotBlank(message = "Фамилия обязательна для заполнения")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @NotBlank(message = "Телефон обязателен для заполнения")
    private String phone;
}
