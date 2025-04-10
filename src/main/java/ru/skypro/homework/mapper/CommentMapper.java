package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.CommentRequest;
import ru.skypro.homework.dto.CommentResponse;
import ru.skypro.homework.dto.CommentUpdateRequest;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment toEntity(CommentRequest commentRequest);

    @Mapping(target = "author", source = "users.id")
    @Mapping(target = "authorFirstName", source = "users.firstName")
    @Mapping(target = "authorImage", source = "users.image", qualifiedByName = "getImagePath")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "pk", source = "id")
    CommentResponse toDto(Comment comment);

    @Mapping(target = "text", source = "text")
    CommentUpdateRequest toUpdateRequest(Comment comment);

    @Named("getImagePath")
    default String getImagePath(String image) {
        if (image == null || image.isEmpty()) {
            return null;
        }
        return "/users/image/" + image;
    }
}