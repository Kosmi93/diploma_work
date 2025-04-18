package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.AuthService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Регистрация")
public class RegisterController {

    private final AuthService authService;
    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<Void> register(@RequestBody RegisterDto register) {
        if (authService.register(register)) {
            log.info("Пользователь {} успешно зарегистрирован", register.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            log.error("Не удалось зарегистрировать пользователя: {}", register.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
