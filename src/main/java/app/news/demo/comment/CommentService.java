package app.news.demo.comment;

import app.news.demo.comment.dto.CommentCreateRequest;
import app.news.demo.comment.dto.CommentResponse;
import app.news.demo.comment.dto.CommentUpdateRequest;
import app.news.demo.post.PostEntity;
import app.news.demo.post.PostRepository;
import app.news.demo.toDto;
import app.news.demo.user.UserEntity;
import app.news.demo.user.UserRoles;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse createComment(CommentCreateRequest commentToCreate, UserEntity user, Long postID) throws IllegalAccessException {
        if (commentToCreate.content() == null) {
            throw new IllegalArgumentException("Write comment content");
        }
        if (!user.isActive()) {
            throw new IllegalAccessException("You are banned");
        }
        CommentEntity parent = commentRepository.findById(postID).orElse(null);
        PostEntity post = postRepository.findById(postID)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        CommentEntity commentEntity = new CommentEntity(
                null,
                commentToCreate.content(),
                user,
                post,
                parent,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        commentRepository.save(commentEntity);
        return toDto.toResponseComment(commentEntity);
    }

    public CommentResponse updateComment(CommentUpdateRequest commentToUpdate, Long id, UserEntity user) throws IllegalAccessException, AccessDeniedException {
        if (commentToUpdate.content() == null) {
            throw new IllegalArgumentException("Write comment content");
        }
        if (!user.isActive()) {
            throw new IllegalAccessException("You are banned");
        }
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if (commentEntity.getAuthor() != user || user.getRole() != UserRoles.ADMIN) {
            throw new AccessDeniedException("You can`t update this comment");
        }
        if (!commentEntity.getIsActive()) {
            throw new IllegalAccessException("Post is not active(Deleted)");
        }
        commentEntity.setContent(commentToUpdate.content());
        commentRepository.save(commentEntity);
        return toDto.toResponseComment(commentEntity);
    }

    public String deleteComment(Long id, UserEntity user) throws AccessDeniedException {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if (comment.getAuthor() != user || user.getRole() != UserRoles.ADMIN) {
            throw new AccessDeniedException("You can`t delete this comment");
        }
        if (!comment.getIsActive()) {
            throw new IllegalArgumentException("Comment already delete");
        }
        comment.setIsActive(false);
        commentRepository.save(comment);
        return "Comment is deleted";
    }
}
