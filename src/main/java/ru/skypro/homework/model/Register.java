package ru.skypro.homework.model;

import lombok.Data;

@Data
public class Register {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    //private Role role;
}
