package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.mapper.UserMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    private AuthServiceImpl authService;
    private MyUserDetailsService myUserDetailsService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        myUserDetailsService = mock(MyUserDetailsService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        authService = new AuthServiceImpl(myUserDetailsService, passwordEncoder, userRepository, new UserMapper());
    }

    @Test
    void login_ReturnsTrue_WhenUserExistsAndPasswordMatches() {
        String username = "john.doe";
        String rawPassword = "12345";
        String encodedPassword = "encodedPass";

        User user = new User();
        user.setUserName(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        boolean result = authService.login(username, rawPassword);
        assertTrue(result);
        verify(userRepository).findByUserName(username);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
    }

    @Test
    void login_ReturnsFalse_WhenUserNotFound() {
        String username = "not.exist";

        when(userRepository.findByUserName(username)).thenReturn(Optional.empty());
        boolean result = authService.login(username, "any");
        assertFalse(result);
        verify(userRepository).findByUserName(username);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void login_ReturnsFalse_WhenPasswordDoesNotMatch() {
        String username = "john.doe";
        String rawPassword = "wrong";
        String encodedPassword = "encodedPass";

        User user = new User();
        user.setUserName(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        boolean result = authService.login(username, rawPassword);
        assertFalse(result);
    }

    @Test
    void register_ReturnsTrue_WhenNewUser() {
        RegisterDto dto = RegisterDto.builder()
                .username("new.user")
                .password("123")
                .firstName("New")
                .lastName("User")
                .phone("123456")
                .role(RoleDto.USER)
                .build();

        when(userRepository.findByUserName(dto.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encoded123");
        boolean result = authService.register(dto);
        assertTrue(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ReturnsFalse_WhenUserAlreadyExists() {
        RegisterDto dto = RegisterDto.builder()
                .username("existing.user")
                .password("123")
                .build();

        when(userRepository.findByUserName(dto.getUsername())).thenReturn(Optional.of(new User()));
        boolean result = authService.register(dto);
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }
}
