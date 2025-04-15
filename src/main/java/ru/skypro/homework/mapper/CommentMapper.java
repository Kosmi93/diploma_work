package ru.skypro.homework.mapper;

import lombok.Builder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {
    public static CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .author(comment.getAuthor().getId())
                .pk(comment.getAd().getPk())
                .authorImage(comment.getAuthor().getImage())
                .authorFirstName(comment.getAuthor().getFirstName())
                .createdAt(comment.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli())
                .text(comment.getText())
                .build();
    }
    public static CommentListResponse toCommentListResponse(List<Comment> comments) {
        return CommentListResponse.builder()
                .count(comments.size())
                .results(comments.stream()
                        .map(s->  CommentResponse.builder()
                                .author(s.getAuthor().getId())
                                .pk(s.getAd().getPk())
                                .authorImage(s.getAuthor().getImage())
                                .authorFirstName(s.getAuthor().getFirstName())
                                .createdAt(s.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli())
                                .text(s.getText())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
