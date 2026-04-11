package app.news.demo.controller;

import app.news.demo.entity.PostEntity;
import app.news.demo.entity.PostResponse;
import app.news.demo.entity.UserEntity;
import app.news.demo.repository.PostRepository;
import app.news.demo.repository.UserRepository;
import app.news.demo.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostService postService;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository, PostService postService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(
            @RequestBody PostResponse postToCreate,
            Principal principal
            ) throws IllegalAccessException {
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        log.info("User - {}. Called createPost with content: {}", postToCreate, user);
        return ResponseEntity.ok(postService.createPost(postToCreate, user));
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAllPost(){
        log.info("Called getAllPost");
        List<PostEntity> posts = PostService.getAllPost();
        return ResponseEntity.ok(posts.stream().map(postService::toResponsePost).toList());
    }

}
