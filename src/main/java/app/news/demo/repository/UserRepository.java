package app.news.demo.repository;

import app.news.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsByEmail(String email);
    List<String> findAllPostById(Long id);
}
