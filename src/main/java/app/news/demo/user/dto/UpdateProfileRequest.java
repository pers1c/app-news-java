package app.news.demo.user.dto;

public record UpdateProfileRequest(
        String firstName,
        String lastName,
        String bio,
        String avatarUrl
) { }
