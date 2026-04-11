package app.news.demo.controller;
import app.news.demo.entity.RefreshToken;
import app.news.demo.entity.UserEntity;
import app.news.demo.jwt.SigninRequest;
import app.news.demo.jwt.SingupRequest;
import app.news.demo.jwt.UserDetailImpl;
import app.news.demo.repository.RefreshTokenRepository;
import app.news.demo.repository.UserRepository;
import app.news.demo.UserRoles;
import app.news.demo.jwt.JwtCore;
import app.news.demo.service.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class SecurityController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RefreshTokenService refreshTokenService;
    private RefreshTokenRepository refreshTokenRepository;
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;

    private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    public void setRefreshTokenService(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @Autowired
    public void setRefreshTokenRepository(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtCore(JwtCore jwtCore) {
        this.jwtCore = jwtCore;
    }

    @PostMapping("/registr")
    public ResponseEntity<?> signup(@RequestBody SingupRequest singupRequest) {
        if (userRepository.existsUserByUsername(singupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different name");
        }
        if (userRepository.existsByEmail(singupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different email");
        }
        UserEntity user = new UserEntity(
                null,
                singupRequest.getUsername(),
                singupRequest.getEmail(),
                passwordEncoder.encode(singupRequest.getPassword()),
                UserRoles.USER,
                true
        );
        userRepository.save(user);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/login")
    ResponseEntity<?> signing(@RequestBody SigninRequest signinRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    signinRequest.getUsername(),
                    signinRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        UserEntity user = userRepository.findUserByUsername(userDetail.getUsername()).orElseThrow();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return ResponseEntity.ok(Map.of(
                "accessToken", jwt,
                "refreshToken", refreshToken.getToken()
        ));
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> payload) {
        String requestToken = payload.get("refreshToken");
        return refreshTokenRepository.findByToken(requestToken)
                .map(token -> {
                    if (refreshTokenService.isTokenExpired(token)) {
                        refreshTokenRepository.delete(token);
                        return ResponseEntity.badRequest().body("Токен обновления истек. Пожалуйста, войдите снова.");
                    }
                    String newJwt = jwtCore.generateToken(token.getUser().getUsername());
                    return ResponseEntity.ok(Map.of("token", newJwt));
                })
                .orElse(ResponseEntity.badRequest().body("Недействительный токен обновления."));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody Map<String, String> payload) {
        String requestToken = payload.get("refreshToken");

        if (requestToken == null || requestToken.isBlank()) {
            return ResponseEntity.badRequest().body("Требуется токен обновления.");
        }

        return refreshTokenRepository.findByToken(requestToken)
                .map(token -> {
                    refreshTokenRepository.delete(token);
                    return ResponseEntity.ok("Выход из системы выполнен успешно.");
                })
                .orElse(ResponseEntity.badRequest().body("Недействительный токен обновления."));
    }
}
