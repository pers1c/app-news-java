package app.news.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record User (
    @Null
    Long id,
    @NotNull
    String username,
    @NotNull
    @Email
    String email,
    @NotNull
    String password,
    @NotNull
    UserRoles role,
    @NotNull
    boolean isActive
){

}
