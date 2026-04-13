package app.news.demo.post;

import app.news.demo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

        @Modifying
        @Query("UPDATE PostEntity p SET p.viewsCount = p.viewsCount + 1 WHERE p.id = :id")
        void incrementViewCount(@Param("id") Long id);
}
