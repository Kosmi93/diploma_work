package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImgService;
import ru.skypro.homework.service.UserService;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImgService imgService;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, ImgService imgService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.imgService = imgService;
    }
    @Override
    public void setPassword(SetPasswordDto newPasswordDto, String username) {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException("User is not found"));
        if (passwordEncoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new InvalidPasswordException();
        }

    }

    @Override
    public UserDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = (userRepository.findByUserName(userName)).orElseThrow();
        return userMapper.toUserDto(user);
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found for update");
        }
        User user = userOptional.get();
        User updateUser = userMapper.toUpdateUser(updateUserDto, user);
        userRepository.save(updateUser);

        return userMapper.toUpdateUserDto(updateUserDto,user);
    }

    @Override
    public void updateUserImage(MultipartFile file) throws IOException {
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (userRepository.findByUserName(username)).orElseThrow();
        user.setImage(imgService.save(user.getId(), file,path));
        userRepository.save(user);
*/
/*        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username is not found"));
            if (user.getImage() == null) {
                user.setImage(pathToDefaultUserImage);
            }
            return Files.readAllBytes(Path.of(user.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        String savedImagePath = imgService.uploadImg(user.getId(), file); // путь к сохранённому файлу
        user.setImage(savedImagePath);
        userRepository.save(user);
    }
}
