package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public ResponseEntity<Void> setPassword(@RequestBody SetPasswordDto password, Authentication authentication) {
        try {
            userService.setPassword(password, authentication.getName());
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public ResponseEntity<UserDto> getAuthUser() {
        UserDto user = userService.getUser();
        return ResponseEntity.ok(user);
    }


    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public ResponseEntity<UpdateUserDto> updateUser(@Valid @RequestBody UpdateUserDto updateUser) {
        UpdateUserDto updatedUser = userService.updateUser(updateUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) throws IOException {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}
