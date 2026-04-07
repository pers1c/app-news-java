package app.news.demo.comment;
import app.news.demo.post.PostEntity;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "comments")
@Entity
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
    @JoinColumn(name = "postId")
    private PostEntity postId;
    @Column(name = "createdAt")
    private LocalDateTime createAt;

    public CommentEntity() {
    }

    public CommentEntity(Long id, String content, UserEntity authorId, PostEntity postId, LocalDateTime createAt) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UserEntity getAuthorId() {
        return authorId;
    }

    public PostEntity getPostId() {
        return postId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(UserEntity authorId) {
        this.authorId = authorId;
    }

    public void setPostId(PostEntity postId) {
        this.postId = postId;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
