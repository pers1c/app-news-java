package app.news.demo.user.dto;

import app.news.demo.user.UserRoles;
import app.news.demo.user.UserEntity;

public record UserResponse(
        Long id,
        String username,
        String email,
        UserRoles role
)
{
    public static UserResponse from(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
