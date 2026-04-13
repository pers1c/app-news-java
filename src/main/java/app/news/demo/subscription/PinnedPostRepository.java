package app.news.demo.subscription;

import app.news.demo.post.PostEntity;
import app.news.demo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinnedPostRepository extends JpaRepository<PinnedPostEntity, Integer> {
    PinnedPostEntity findByUser(UserEntity user);
    PinnedPostEntity findByPost(PostEntity post);
    Boolean existsByUser(UserEntity user);
}
