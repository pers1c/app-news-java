package app.news.demo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "comments")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private UserEntity authorId;
    @ManyToOne()
    @JoinColumn(name = "post")
    private PostEntity post;
    @Column(name = "createdAt")
    private LocalDateTime createAt;
}
