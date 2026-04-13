package app.news.demo.post.dto;

import app.news.demo.category.CategoryEntity;
import app.news.demo.post.PostStatus;

public record PostCreateRequest(
        String title,
        String content,
        CategoryEntity category,
        PostStatus status
) { }
