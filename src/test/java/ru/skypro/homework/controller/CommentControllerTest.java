package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;
import ru.skypro.homework.config.TestSecurityConfig;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@Import(TestSecurityConfig.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /ads/{adId}/comments - success")
    void getCommentsByAdId_shouldReturnComments() throws Exception {
        int adId = 1;
        CommentResponse commentResponse = CommentResponse.builder()
                .author(1)
                .authorFirstName("John")
                .text("Nice ad!")
                .createdAt(System.currentTimeMillis())
                .pk(10)
                .authorImage("img.png")
                .build();

        CommentListResponse listResponse = CommentListResponse.builder()
                .count(1)
                .results(List.of(commentResponse))
                .build();

        when(commentService.getCommentsByAdId(adId)).thenReturn(listResponse);

        mockMvc.perform(get("/ads/{adId}/comments", adId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.results[0].text").value("Nice ad!"));
    }

    @Test
    @DisplayName("POST /ads/{adId}/comments - success")
    void addComment_shouldReturnCreatedComment() throws Exception {
        int adId = 1;
        CommentRequest request = new CommentRequest();
        request.setText("This is a comment");

        CommentResponse response = CommentResponse.builder()
                .pk(123)
                .text("This is a comment")
                .author(1)
                .authorFirstName("Alice")
                .authorImage("img.jpg")
                .createdAt(System.currentTimeMillis())
                .build();

        when(commentService.addComment(eq(adId), any(CommentRequest.class))).thenReturn(response);

        mockMvc.perform(post("/ads/{adId}/comments", adId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("This is a comment"));
    }

    @Test
    @DisplayName("DELETE /ads/{adId}/comments/{commentId} - success")
    void deleteComment_shouldReturnOk() throws Exception {
        int adId = 1;
        int commentId = 2;

        doNothing().when(commentService).deleteComment(adId, commentId);

        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", adId, commentId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PATCH /ads/{adId}/comments/{commentId} - success")
    void updateComment_shouldReturnUpdatedComment() throws Exception {
        int adId = 1;
        int commentId = 2;

        CommentUpdateRequest updateRequest = new CommentUpdateRequest();
        updateRequest.setText("Updated text");

        CommentResponse updatedResponse = CommentResponse.builder()
                .pk(commentId)
                .text("Updated text")
                .author(1)
                .authorFirstName("UpdatedName")
                .createdAt(System.currentTimeMillis())
                .build();

        when(commentService.updateComment(eq(adId), eq(commentId), any(CommentUpdateRequest.class)))
                .thenReturn(updatedResponse);

        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", adId, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Updated text"));
    }
}
