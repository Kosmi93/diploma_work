package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.model.Ad;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdsDto {
    @Schema(description = "Общее количество объявлений", example = "3")
    private Long count;
    @Schema(description = "Список объявлений")
    private List<Ad> result;
}
