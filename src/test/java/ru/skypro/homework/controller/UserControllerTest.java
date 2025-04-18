package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.config.TestSecurityConfig;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.InvalidPasswordException;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

        @Test
    @WithMockUser
    void getAuthUser_shouldReturnUserDto() throws Exception {
        UserDto dto = UserDto.builder()
                .id(1)
                .email("email@example.com")
                .firstName("Test")
                .lastName("User")
                .phone("12345")
                .build();

        Mockito.when(userService.getUser()).thenReturn(dto);

        mockMvc.perform(get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("email@example.com"))
                .andExpect(jsonPath("$.firstName").value("Test"));
    }

    @Test
    @WithMockUser
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        UpdateUserDto request = UpdateUserDto.builder()
                .firstName("New")
                .lastName("Name")
                .phone("98765")
                .build();

        Mockito.when(userService.updateUser(any(UpdateUserDto.class))).thenReturn(request);

        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("New"));
    }

    @Test
    @WithMockUser
    void updateUserImage_shouldReturnOk() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", "avatar.png", MediaType.IMAGE_PNG_VALUE, "dummy".getBytes());

        mockMvc.perform(
                        multipart("/users/me/image")
                                .file(file)
                                .with(request -> {
                                    request.setMethod("PATCH");
                                    return request;
                                })
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getUserImage_shouldReturnImageBytes() throws Exception {
        byte[] image = "test image".getBytes();
        Mockito.when(userService.getImage("testUser")).thenReturn(image);

        mockMvc.perform(get("/users/testUser/image"))
                .andExpect(status().isOk())
                .andExpect(content().bytes(image));
    }
}
