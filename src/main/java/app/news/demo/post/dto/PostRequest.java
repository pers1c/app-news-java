package app.news.demo.post.dto;

import app.news.demo.post.PostStatus;

public record PostRequest(
        String title,
        String content,
        String category,
        PostStatus status
) {
}
