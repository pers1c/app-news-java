package app.news.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        String title,
        String content,
        UserResponse author,
        String category,
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
