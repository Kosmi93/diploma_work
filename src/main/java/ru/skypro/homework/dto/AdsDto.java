package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.skypro.homework.model.Ad;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdsDto {
    @Schema(description = "Общее количество объявлений", example = "3")
    private int count;
    @Schema(description = "Список объявлений")
    private List<AdDto> results;
}
