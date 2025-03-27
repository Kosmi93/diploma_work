package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Информации об объявлении")
public class ExtendedAd {
    @Schema(description = "Id объявления", example = "13221")
    private int pk;
    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;
    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String authorLastName;
    @Schema(description = "Описание объявления", example = "это объявление")
    private String description;
    @Schema(description = "Логин автора объявления", example = "ivan@mail.ru")
    private String email;
    @Schema(description = "Ссылка на картинку объявления", example = "/img-ads/38292.jpg")
    private String image;
    @Schema(description = "телефон автора объявления", example = "88888888888")
    private String phone;
    @Schema(description = "Цена", example = "38292,00")
    private String price;
    @Schema(description = "Заголовок объявления", example = "Товар")
    private String title;
}
