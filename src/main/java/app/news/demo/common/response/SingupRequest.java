package app.news.demo.common.response;

import lombok.Data;

@Data
public class SingupRequest {
    private String username;
    private String email;
    private String password;
}
