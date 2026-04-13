package app.news.demo.service;

import app.news.demo.common.security.RefreshToken;
import app.news.demo.user.UserEntity;
import app.news.demo.common.security.RefreshTokenRepository;
import app.news.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class RefreshTokenService {
    @Value("${testing.app.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository repo, UserRepository userRepo) {
        this.refreshTokenRepository = repo;
        this.userRepository = userRepo;
    }

    public RefreshToken createRefreshToken(Long userId) {
        var token = new RefreshToken();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);
        token.setUser(userRepository.findById(userId).get());
        token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        token.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(token);
    }

    public boolean isTokenExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }
}