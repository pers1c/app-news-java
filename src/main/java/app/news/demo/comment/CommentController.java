package app.news.demo.comment;

import app.news.demo.comment.dto.CommentCreateRequest;
import app.news.demo.comment.dto.CommentResponse;
import app.news.demo.comment.dto.CommentUpdateRequest;
import app.news.demo.post.PostController;
import app.news.demo.user.UserEntity;
import app.news.demo.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentController(CommentService commentService, CommentRepository commentRepository, UserRepository userRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody CommentCreateRequest commentToCreate,
            @PathVariable Long id,
            Principal principal
    ) throws IllegalAccessException {
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        log.info("User - {}. Called createPost with content: {}", user, commentToCreate);
        return ResponseEntity.ok(commentService.createComment(commentToCreate, user, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @RequestBody CommentUpdateRequest commentToUpdate,
            @PathVariable Long id,
            Principal principal
            ) throws AccessDeniedException, IllegalAccessException {
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        log.info("User - {}. Called updatePost with content: {}", user, commentToUpdate);
        return ResponseEntity.ok(commentService.updateComment(commentToUpdate, id, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long id,
            Principal principal
    ) throws AccessDeniedException {
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        log.info("User - {}. Called deletePost with content: {}", user, id);
        return ResponseEntity.ok(commentService.deleteComment(id, user));
    }
}
