package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class CommentController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentListResponse> getComments(
            @PathVariable("id") Integer adId) {
        // Заглушка - в реальном проекте заменить на реализацию
        return ResponseEntity.ok(new CommentListResponse(0, List.of()));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable("id") Integer adId,
            @RequestBody CommentRequest commentRequest) {
        // Заглушка - в реальном проекте заменить на реализацию
        return ResponseEntity.ok(new CommentResponse());
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId) {
        // Заглушка - в реальном проекте заменить на реализацию
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody CommentUpdateRequest commentUpdateRequest) {
        // Заглушка - в реальном проекте заменить на реализацию
        return ResponseEntity.ok(new CommentResponse());
    }
}