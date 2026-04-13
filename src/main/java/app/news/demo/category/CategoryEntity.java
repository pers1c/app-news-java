package app.news.demo.category;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "category")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "slug")
    private String slug;
    @Column(name = "description")
    private String description;
    @Column(name = "createAt")
    private LocalDateTime createdAt;
}
