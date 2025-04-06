package ru.skypro.homework.service.impl;

//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;

import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        return encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        Optional<User> userOptional = userRepository.findByUserName(registerDto.getUserName());
        if (userOptional.isPresent()) {
            return false;
        }
        User user = userMapper.toRegisterUser(registerDto);
        user.setPassword(encoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        return true;
    }
}
