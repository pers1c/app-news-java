package app.news.demo.jwt;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
