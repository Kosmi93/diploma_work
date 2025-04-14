package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdDto {

    private Integer pk;
    private Integer author;
    private String image;
    private Integer price;
    private String title;
    private String description;
}

