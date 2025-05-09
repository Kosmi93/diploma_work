package ru.skypro.homework.dto;

import lombok.*;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDto role;
    private String image;
}