package app.news.demo;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String email;
    private String password;
}
