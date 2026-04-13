package app.news.demo.payment.dto;

import app.news.demo.post.PostEntity;

public record CheckoutRequest(
        PostEntity post,
        String successUrl,
        String cancelUrl
) {
}
