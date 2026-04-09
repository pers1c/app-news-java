package app.news.demo.jwt;

import lombok.Data;

@Data
public class SingupRequest {
    private String username;
    private String email;
    private String password;
}
