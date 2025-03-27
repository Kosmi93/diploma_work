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
@Schema(description = "CreateOrUpdateAd")
public class CreateOrUpdateAd {
    @Schema(description = "Заголовок объявления", example = "Товар")
    private String title;
    @Schema(description = "Цена", example = "38292,00")
    private double price;
    @Schema(description = "Описание объявления", example = "это объявление")
    private String description;

}

