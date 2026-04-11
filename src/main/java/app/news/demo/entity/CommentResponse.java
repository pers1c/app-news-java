package app.news.demo.entity;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        UserResponse author,
        LocalDateTime createAt
) {
    public static CommentResponse from(CommentEntity comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                UserResponse.from(comment.getAuthor()),
                comment.getCreateAt()
        );
    }
}

