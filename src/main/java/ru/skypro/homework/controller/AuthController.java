package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.service.AuthService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor  // Lombok генерирует конструктор для final полей
@Tag(name = "Авторизация")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto login) {
        log.info("Попытка логина с логином: {}", login.getUserName());  // Логируем попытку входа

        if (authService.login(login.getUserName(), login.getPassword())) {
            log.info("Пользователь {} успешно авторизован", login.getUserName());  // Логируем успешный вход
            return ResponseEntity.ok().build();
        } else {
            log.warn("Неудачная попытка входа для пользователя: {}", login.getUserName());  // Логируем неудачную попытку входа
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}