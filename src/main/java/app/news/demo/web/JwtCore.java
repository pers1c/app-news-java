package app.news.demo.web;

import app.news.demo.user.UserDetailImpl;
import lombok.Data;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.net.Authenticator;
import java.util.Date;

@Component
public class JwtCore {
    @Value("${testing.app.secret")
    private String secret;
    @Value("${testing.app.lifetime")
    private int lifetime;

    public String generateToken(Authentication authentication){
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((userDetail.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Data()).getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public String getNameFromJwt(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBodt().getSubject;
    }
}
