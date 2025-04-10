package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterDto register);
}
