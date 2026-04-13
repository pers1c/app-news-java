package app.news.demo.user;

import app.news.demo.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsByEmail(String email);
//    List<PostEntity> findPostsByAuthor(UserEntity user);
}
