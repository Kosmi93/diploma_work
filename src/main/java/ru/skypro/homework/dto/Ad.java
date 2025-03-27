package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Объявление")
public class Ad {
    //связать с классом юзер через автора
    @Schema(description = "Идентификатор автора объявления", example = "38292")
    private Long author;
    @Schema(description = "Ссылка на картинку объявления", example = "/img-ads/38292.jpg")
    private String image;
    @Schema(description = "Идентификатор объявления", example = "13221")
    private Long pk; //это id
    @Schema(description = "Цена", example = "38292,00")
    private double price;
    @Schema(description = "Заголовок объявления", example = "Товар")
    private String title;


}
