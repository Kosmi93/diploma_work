package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Информации об объявлении")
@Builder
public class ExtendedAdDto {
    @Schema(description = "Id объявления", example = "13221")
    private Integer pk;
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
    private Integer price;
    @Schema(description = "Заголовок объявления", example = "Товар")
    private String title;
}
