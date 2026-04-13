package app.news.demo.post.dto;

import app.news.demo.category.CategoryEntity;
import app.news.demo.comment.dto.CommentResponse;
import app.news.demo.post.PostEntity;
import app.news.demo.user.dto.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        String title,
        String content,
        UserResponse author,
        CategoryEntity category,
        List<CommentResponse> comments,
        LocalDateTime createAt,
        LocalDateTime updateAt,
        boolean pinned
) {
    public static PostResponse from(PostEntity post, List<CommentResponse> comments) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                UserResponse.from(post.getAuthor()),
                post.getCategory(),
                comments,
                post.getCreateAt(),
                post.getUpdatedAt(),
                post.isPinned()
        );
}
}
