package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "CreateOrUpdateAd")
public class CreateOrUpdateAdDto {
    @Schema(description = "Заголовок объявления", example = "Товар")
    private String title;
    @Schema(description = "Цена", example = "38292,00")
    private Integer price;
    @Schema(description = "Описание объявления", example = "это объявление")
    private String description;

}

