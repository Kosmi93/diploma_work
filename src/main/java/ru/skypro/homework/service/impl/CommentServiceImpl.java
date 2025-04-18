package ru.skypro.homework.service.impl;

import org.springframework.expression.AccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;
import ru.skypro.homework.exception.AccessUserException;
import ru.skypro.homework.exception.AdNameNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;


    public CommentServiceImpl(UserRepository userRepository, AdRepository adRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentListResponse getCommentsByAdId(Integer adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new AdNameNotFoundException("AdName not found"));
        List<Comment> comments = ad.getComments();
        return CommentMapper.toCommentListResponse(comments);
    }

    @Override
    public CommentResponse addComment(Integer adId, CommentRequest commentRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new AdNameNotFoundException("AdName not found"));

        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setAd(ad);
        comment.setText(commentRequest.getText());
        comment.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return CommentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment is not found"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        commentRepository.delete(comment);
    }

    @Override
    public CommentResponse updateComment(Integer adId, Integer commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment is not found"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return CommentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Override
    public boolean verificationAuthorComment(Integer commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return false;
        }
        String username = authentication.getName();
        User user = userRepository.findByUserName(username)
                .orElse(null);
        if (user == null) {
            return false;
        }
        Comment comment = commentRepository.findById(commentId)
                .orElse(null);
        if (comment == null || comment.getAuthor() == null) {
            return false;
        }
        return comment.getAuthor().getId().equals(user.getId());
    }
}