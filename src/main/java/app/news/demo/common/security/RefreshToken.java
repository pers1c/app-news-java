package app.news.demo.common.security;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Table(name = "refresh")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;
}
