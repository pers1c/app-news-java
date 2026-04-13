package app.news.demo.subscription;

import app.news.demo.post.PostEntity;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "pinnedPost")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PinnedPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToOne
    @JoinColumn(name = "post")
    private PostEntity post;
    @Column(name = "pinnedAt")
    private LocalDateTime pinnedAt;

}
