package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;

public interface CommentService {
    CommentListResponse getCommentsByAdId(Integer adId);
    CommentResponse addComment(Integer adId, CommentRequest commentRequest);
    void deleteComment(Integer adId, Integer commentId);
    CommentResponse updateComment(Integer adId, Integer commentId, CommentUpdateRequest commentUpdateRequest);
}