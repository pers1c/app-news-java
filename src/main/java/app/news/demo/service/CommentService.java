package app.news.demo.service;

import app.news.demo.entity.*;
import app.news.demo.repository.CommentRepository;
import app.news.demo.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse createComment(CommentResponse commentToCreate, UserEntity user, Long postID) throws IllegalAccessException {
        if (commentToCreate.content() == null){
            throw new IllegalArgumentException("Write comment content");
        }
        if (!user.isActive()){
            throw new IllegalAccessException("You are banned");
        }
        PostEntity post = postRepository.findById(postID)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        CommentEntity commentEntity = new CommentEntity(
                null,
                commentToCreate.content(),
                user,
                post,
                LocalDateTime.now()
        );
        commentRepository.save(commentEntity);
        return CommentResponse.from(commentEntity);
    }
}
