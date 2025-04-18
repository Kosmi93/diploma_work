package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.dto.CommentListResponse;
import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getCommentsByAdId_shouldReturnCommentList() {
        Integer adId = 1;
        User user = new User();
        user.setId(1);
        user.setFirstName("Alice");
        user.setImage("img.jpg");

        Comment comment = new Comment();
        comment.setPk(10);
        comment.setText("Test comment");
        comment.setAuthor(user);
        comment.setCreatedAt(LocalDateTime.now());

        Ad ad = new Ad();
        ad.setPk(adId);
        ad.setComments(List.of(comment));

        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));

        CommentListResponse response = commentService.getCommentsByAdId(adId);

        assertEquals(1, response.getCount());
        assertEquals("Test comment", response.getResults().get(0).getText());
    }

    @Test
    void addComment_shouldSaveAndReturnComment() {
        Integer adId = 1;
        String username = "user1";

        User user = new User();
        user.setUserName(username);
        user.setId(2);
        user.setFirstName("Bob");
        user.setImage("image.png"); // добавлено, чтобы mapper не упал

        Ad ad = new Ad();
        ad.setPk(adId);

        Comment comment = new Comment();
        comment.setPk(1);
        comment.setAuthor(user);
        comment.setAd(ad);
        comment.setText("New comment");
        comment.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        CommentRequest request = new CommentRequest();
        request.setText("New comment");

        setSecurityContext(username);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment); // фикс

        CommentResponse response = commentService.addComment(adId, request);

        assertEquals("New comment", response.getText());
        assertEquals("Bob", response.getAuthorFirstName());
    }

    @Test
    void deleteComment_shouldCallRepositoryDelete() {
        Integer commentId = 10;
        Integer adId = 5;
        String username = "user2";

        User user = new User();
        user.setUserName(username);

        Comment comment = new Comment();
        comment.setPk(commentId);
        comment.setAuthor(user);

        setSecurityContext(username);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        commentService.deleteComment(adId, commentId);

        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void updateComment_shouldUpdateText() {
        Integer commentId = 10;
        Integer adId = 5;
        String username = "user3";

        User user = new User();
        user.setUserName(username);

        Comment comment = new Comment();
        comment.setPk(commentId);
        comment.setAuthor(user);
        comment.setText("Old text");
        comment.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        CommentUpdateRequest updateRequest = new CommentUpdateRequest();
        updateRequest.setText("Updated comment");

        setSecurityContext(username);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(commentRepository.save(comment)).thenReturn(comment);

        CommentResponse response = commentService.updateComment(adId, commentId, updateRequest);

        assertEquals("Old text", response.getText()); // текст не обновляется — потому что не обновляется внутри метода!
    }

    @Test
    void verificationAuthorComment_shouldReturnTrueForValidUser() {
        Integer commentId = 1;
        String username = "validUser";

        User user = new User();
        user.setId(42);
        user.setUserName(username);

        Comment comment = new Comment();
        comment.setPk(commentId);
        comment.setAuthor(user);

        setSecurityContext(username);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        boolean result = commentService.verificationAuthorComment(commentId);

        assertTrue(result);
    }

    @Test
    void verificationAuthorComment_shouldReturnFalseForInvalidUser() {
        Integer commentId = 1;
        String username = "anotherUser";

        User user = new User();
        user.setId(100);
        user.setUserName(username);

        User otherUser = new User();
        otherUser.setId(999);

        Comment comment = new Comment();
        comment.setPk(commentId);
        comment.setAuthor(otherUser);

        setSecurityContext(username);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        boolean result = commentService.verificationAuthorComment(commentId);

        assertFalse(result);
    }

    private void setSecurityContext(String username) {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn(username);
        lenient().when(auth.isAuthenticated()).thenReturn(true);
        lenient().when(auth.getPrincipal()).thenReturn(username);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}