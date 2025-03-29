package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class CommentController {

    // GET /ads/{id}/comments - Получение комментариев объявления
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentListResponse> getComments(
            @PathVariable("id") Integer adId) {
        // Реализация метода
        return ResponseEntity.ok(new CommentListResponse(0, List.of()));
    }

    // POST /ads/{id}/comments - Добавление комментария
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable("id") Integer adId,
            @RequestBody CommentRequest commentRequest) {
        // Реализация метода
        return ResponseEntity.ok(new CommentResponse());
    }

    // DELETE /ads/{adId}/comments/{commentId} - Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId) {
        // Реализация метода
        return ResponseEntity.ok().build();
    }

    // PATCH /ads/{adId}/comments/{commentId} - Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody CommentUpdateRequest commentUpdateRequest) {
        // Реализация метода
        return ResponseEntity.ok(new CommentResponse());
    }

    // DTO классы
    public static class CommentRequest {
        private String text;

        // Геттеры и сеттеры
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class CommentUpdateRequest {
        private String text;

        // Геттеры и сеттеры
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class CommentResponse {
        private Integer author;
        private String authorImage;
        private String authorFirstName;
        private Long createdAt;
        private Integer pk;
        private String text;

        // Геттеры и сеттеры
        public Integer getAuthor() {
            return author;
        }

        public void setAuthor(Integer author) {
            this.author = author;
        }

        public String getAuthorImage() {
            return authorImage;
        }

        public void setAuthorImage(String authorImage) {
            this.authorImage = authorImage;
        }

        public String getAuthorFirstName() {
            return authorFirstName;
        }

        public void setAuthorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
        }

        public Long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getPk() {
            return pk;
        }

        public void setPk(Integer pk) {
            this.pk = pk;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class CommentListResponse {
        private Integer count;
        private List<CommentResponse> results;

        public CommentListResponse(Integer count, List<CommentResponse> results) {
            this.count = count;
            this.results = results;
        }

        // Геттеры и сеттеры
        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<CommentResponse> getResults() {
            return results;
        }

        public void setResults(List<CommentResponse> results) {
            this.results = results;
        }
    }
}
