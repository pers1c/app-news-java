package app.news.demo.comment;

import java.time.LocalDateTime;

public record Comment (
        Long id,
        String content,
        Long authorId,
        Long postId,
        LocalDateTime createdAt
){

}
