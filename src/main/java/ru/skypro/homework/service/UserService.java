package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {
    boolean setPassword(SetPasswordDto newPasswordDto) throws UserNotFoundException, InvalidPasswordException;
    UserDto getUser();
    UpdateUserDto updateUser(UpdateUserDto updateUserDto);
    void updateUserImage(MultipartFile file);
}
