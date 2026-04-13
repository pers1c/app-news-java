package app.news.demo.comment.dto;

import app.news.demo.post.PostEntity;

public record CommentCreateRequest(
        String content,
        PostEntity post,
        CommentResponse parent
) {
}
