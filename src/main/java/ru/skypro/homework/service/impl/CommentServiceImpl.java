package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentListResponse getCommentsByAdId(Integer adId) {
        // Реализация получения списка комментариев по ID объявления
        return null;
    }

    @Override
    public CommentResponse addComment(Integer adId, CommentRequest commentRequest) {
        // Реализация добавления нового комментария
        return null;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        // Реализация удаления комментария
    }

    @Override
    public CommentResponse updateComment(Integer adId, Integer commentId, CommentUpdateRequest commentUpdateRequest) {
        // Реализация обновления комментария
        return null;
    }
}