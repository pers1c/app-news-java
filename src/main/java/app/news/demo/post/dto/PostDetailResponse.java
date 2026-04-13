package app.news.demo.post.dto;

import app.news.demo.comment.dto.CommentResponse;
import app.news.demo.category.CategoryEntity;
import app.news.demo.post.PostStatus;

import java.util.List;

public record PostDetailResponse(
        String title,
        String content,
        CategoryEntity category,
        List<CommentResponse> comments,
        PostStatus status
) {
}
