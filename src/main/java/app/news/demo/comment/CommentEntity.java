package app.news.demo.comment;
import app.news.demo.post.PostEntity;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "author")
    private UserEntity author;
    @ManyToOne()
    @JoinColumn(name = "post")
    private PostEntity post;
    @ManyToOne
    @JoinColumn(name = "parent")
    private CommentEntity parent;
    @OneToMany(mappedBy = "parent")
    List<CommentEntity> replies;
    @Column(name = "createdAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updateAt;
    @Column(name = "isActive")
    private Boolean isActive;

    public CommentEntity(Object o, String content, UserEntity user, PostEntity post, CommentEntity parent, LocalDateTime now, LocalDateTime now1, boolean b) {
    }
}
