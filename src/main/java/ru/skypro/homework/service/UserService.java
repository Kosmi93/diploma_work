package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    boolean setPassword(SetPasswordDto newPasswordDto);
    UserDto getUser();
    UpdateUserDto updateUser(UpdateUserDto updateUserDto);
    void updateUserImage(MultipartFile file);
}
