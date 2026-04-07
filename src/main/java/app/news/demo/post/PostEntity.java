package app.news.demo.post;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "posts")
@Entity
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private UserEntity authorId;
    @Column(name = "category")
    private String category;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "isPinned")
    private boolean isPinned;

    public PostEntity(Long id, String title, String content, UserEntity authorId, String category, LocalDateTime createAt, LocalDateTime updatedAt, boolean isPinned) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.category = category;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.isPinned = isPinned;
    }

    public PostEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UserEntity getAuthorId() {
        return authorId;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(UserEntity authorId) {
        this.authorId = authorId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
