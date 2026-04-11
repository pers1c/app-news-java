package app.news.demo.entity;

import app.news.demo.UserRoles;

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
