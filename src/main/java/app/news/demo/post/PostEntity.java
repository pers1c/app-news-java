package app.news.demo.post;
import app.news.demo.category.CategoryEntity;
import app.news.demo.comment.CommentEntity;
import app.news.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "posts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "Slug")
    private String slug;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    @Column(name = "viewsCount")
    private Long viewsCount;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "category")
    private CategoryEntity category;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<CommentEntity> comment = new ArrayList<>();
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "isPinned")
    private boolean isPinned;

}
