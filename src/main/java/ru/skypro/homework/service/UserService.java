package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {
    void setPassword(SetPasswordDto newPasswordDto, String username);
    UserDto getUser();
    UpdateUserDto updateUser(UpdateUserDto updateUserDto);
    void updateUserImage(MultipartFile file) throws IOException;
}
