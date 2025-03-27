package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Ads {
    //связать с классом юзер через автора
    private Long author;
    private String image;
    private Long pk; //это id
    private double price;
    private String title;


}
