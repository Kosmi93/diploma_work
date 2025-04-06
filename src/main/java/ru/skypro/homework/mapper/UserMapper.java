package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Component
public class UserMapper {
    public User toUserNewPassword(SetPasswordDto setPasswordDto, User user) {
        return user.builder()
                .password(setPasswordDto.getNewPassword())
                .build();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .role(user.getRole())
                .image(user.getIdImage())
                .build();
    }

    public User toUpdateUser(UpdateUserDto updateUserDto, User user) {
        return user.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .firstName(updateUserDto.getFirstName())
                .lastName(updateUserDto.getLastName())
                .phone(updateUserDto.getPhone())
                .role(user.getRole())
                .idImage(user.getIdImage())
                .build();
    }
     public UpdateUserDto toUpdateUserDto(UpdateUserDto updateUserDto, User user) {
        return UpdateUserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
     }

    public User toRegisterUser(RegisterDto registerDto) {
        User user = new User();
        return user.builder()
                .userName(registerDto.getUserName())
                .password(registerDto.getPassword())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .phone(registerDto.getPhone())
                .role(registerDto.getRole())
                .build();
    }

}
