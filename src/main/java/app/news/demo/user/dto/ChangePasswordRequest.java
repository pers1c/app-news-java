package app.news.demo.user.dto;

public record ChangePasswordRequest(
        String oldPassword,
        String newPassword
) {
}
