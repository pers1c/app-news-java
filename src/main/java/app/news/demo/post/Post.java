package app.news.demo.post;

import java.time.LocalDateTime;

public record Post (
        Long id,
        String title,
        String content,
        Long authorId,
        String category,
        LocalDateTime createAt,
        LocalDateTime updatedAt,
        boolean isPinned
){

}

