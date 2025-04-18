package ru.skypro.homework.service.impl;

import com.sun.xml.bind.v2.runtime.output.Encoded;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImgService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ImgService imgService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private Authentication authentication;
    @Mock
    private SecurityContext securityContext;

    private final String username = "test@example.com";
    @Value("${path.to.imgUser.folder}")
    private String path;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        lenient().when(authentication.getName()).thenReturn(username);
    }

    @Test
    void getUser_ShouldReturnUserDto_WhenUserExists() {
        User user = new User();
        user.setId(1);
        user.setUserName(username);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        UserDto result = userService.getUser();

        assertEquals(1, result.getId());
        assertEquals(username, result.getEmail());
    }

    @Test
    void updateUser_ShouldReturnUpdatedDto_WhenUserExists() {
        User user = new User();
        user.setUserName(username);

        UpdateUserDto dto = UpdateUserDto.builder()
                .firstName("New")
                .lastName("User")
                .phone("1234567890")
                .build();

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        UpdateUserDto result = userService.updateUser(dto);

        assertEquals("New", result.getFirstName());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUserImage_ShouldSaveImageAndUpdateUser() {
        MultipartFile file = mock(MultipartFile.class);
        User user = new User();
        user.setId(1);
        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(imgService.uploadImg(eq(1), eq(file), eq(path))).thenReturn("path/to/image");
        userService.updateUserImage(file);

        assertEquals("path/to/image", user.getImage());
        verify(userRepository).save(user);
    }

    @Test
    void getImage_ShouldReturnImageBytes() throws Exception {
        Path tempImage = Files.createTempFile("test-image", ".jpg");
        byte[] expectedContent = "test image content".getBytes();
        Files.write(tempImage, expectedContent);

        User user = new User();
        user.setUserName(username);
        user.setImage(tempImage.toString());

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        byte[] actualBytes = userService.getImage(username);

        assertNotNull(actualBytes);
        assertArrayEquals(expectedContent, actualBytes);

        Files.deleteIfExists(tempImage);
    }

    @Test
    void getImage_ShouldThrowRuntimeException_WhenIOExceptionOccurs() {
        User user = new User();
        user.setImage("invalid/path");

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, () -> userService.getImage(username));
    }
    @Test
    void setPassword_ShouldUpdatePassword_WhenCurrentPasswordIsCorrect() {
        SetPasswordDto dto = new SetPasswordDto();
        dto.setCurrentPassword("oldPassword");
        dto.setNewPassword("newPassword");

        User user = new User();
        user.setUserName(username);
        user.setPassword("encodedOldPassword");

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("oldPassword", "encodedOldPassword")).thenReturn(true);
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        userService.setPassword(dto, username);

        assertEquals("encodedNewPassword", user.getPassword());
        verify(userRepository).save(user);
    }
}
