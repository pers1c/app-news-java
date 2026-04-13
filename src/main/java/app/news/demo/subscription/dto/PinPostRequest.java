package app.news.demo.subscription.dto;

import app.news.demo.post.PostEntity;

public record PinPostRequest(
        PostEntity post
) {
}
