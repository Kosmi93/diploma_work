package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class AdsInfo  {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    // переделать на картинку
    private String image;
    private String phone;
    private String price;
    private String title;
}
