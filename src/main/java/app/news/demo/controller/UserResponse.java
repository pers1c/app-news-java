package app.news.demo.controller;

import app.news.demo.UserRoles;

public record UserResponse(
        Long id,
        String username,
        String email,
        UserRoles role,
        int postCount) {
}
