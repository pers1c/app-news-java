package app.news.demo.repository;

import app.news.demo.entity.CommentEntity;
import app.news.demo.entity.CommentResponse;
import app.news.demo.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPost(PostEntity post);
}
