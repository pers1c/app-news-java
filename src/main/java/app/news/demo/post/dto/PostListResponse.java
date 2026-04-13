package app.news.demo.post.dto;

import app.news.demo.category.CategoryEntity;
import app.news.demo.post.PostStatus;

public record PostListResponse(
        String title,
        String content,
        CategoryEntity category,
        PostStatus status,
        boolean isPinned

) {
}
