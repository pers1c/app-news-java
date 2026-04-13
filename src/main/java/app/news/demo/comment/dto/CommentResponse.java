package app.news.demo.comment.dto;

import app.news.demo.comment.CommentEntity;
import app.news.demo.post.dto.PostResponse;
import app.news.demo.user.dto.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public record CommentResponse(
        String content,
        UserResponse user,
        PostResponse post,
        CommentResponse parent,
        List<CommentResponse> replies,
        LocalDateTime createAt,
        LocalDateTime updatedAt
) {}

