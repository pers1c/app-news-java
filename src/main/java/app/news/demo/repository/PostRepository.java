package app.news.demo.repository;

import app.news.demo.entity.PostEntity;
import app.news.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
        List<PostEntity> findByAuthorIsActive(boolean isActive);
        List<PostEntity> findByAuthorUsernameAndAuthorIsActive(String username, boolean isActive);
        List<PostEntity> findByCategoryAndAuthorIsActive(String category, boolean isActive);
        Long countByAuthorAndAuthorIsActive(UserEntity author, boolean isActive);
        List<PostEntity> findByAuthorUsernameAndCategoryAndAuthorIsActive(String username, String category, boolean isActive);

        PostEntity findByAuthor(UserEntity user);
}
