package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{adId}/comments")
    @Operation(summary = "Получить комментарии объявления")
    public ResponseEntity<CommentListResponse> getCommentsByAdId(@PathVariable Integer adId) {
        CommentListResponse comments = commentService.getCommentsByAdId(adId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{adId}/comments")
    @Operation(summary = "Добавить комментарий")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Integer adId,
                                                      @RequestBody CommentRequest commentRequest) {
        CommentResponse comment = commentService.addComment(adId, commentRequest);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("@commentServiceImpl.verificationAuthorComment(#commentId)")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
            commentService.deleteComment(adId, commentId);
            return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновить комментарий")
    @PreAuthorize("@commentServiceImpl.verificationAuthorComment(#commentId)")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Integer adId,
                                                         @PathVariable Integer commentId,
                                                         @RequestBody CommentUpdateRequest commentUpdateRequest) {
        CommentResponse updatedComment = commentService.updateComment(adId, commentId, commentUpdateRequest);
        return ResponseEntity.ok(updatedComment);
    }
}