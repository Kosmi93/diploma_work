package ru.skypro.homework.dto;

import lombok.*;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDto role;
    private String image;
}