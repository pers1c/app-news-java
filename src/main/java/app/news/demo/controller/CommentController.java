package app.news.demo.controller;

import app.news.demo.entity.CommentEntity;
import app.news.demo.entity.CommentResponse;
import app.news.demo.entity.UserEntity;
import app.news.demo.repository.CommentRepository;
import app.news.demo.repository.UserRepository;
import app.news.demo.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private CommentService commentService;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentController(CommentService commentService, CommentRepository commentRepository, UserRepository userRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody CommentResponse commentToCreate,
            @PathVariable Long id,
            Principal principal
    ) throws IllegalAccessException {
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        log.info("User - {}. Called createPost with content: {}", commentToCreate, user);
        return ResponseEntity.ok(commentService.createComment(commentToCreate, user, id));
    }
}
