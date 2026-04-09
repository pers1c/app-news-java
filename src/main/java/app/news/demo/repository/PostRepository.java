package app.news.demo.repository;

import app.news.demo.entity.PostEntity;
import app.news.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
        List<String> findAllByAuthorIs(UserEntity author);
}
