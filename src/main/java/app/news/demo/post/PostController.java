package app.news.demo.post;

import app.news.demo.category.CategoryEntity;
import app.news.demo.category.CategoryRepository;
import app.news.demo.category.dto.CategoryRequest;
import app.news.demo.post.dto.PostRequest;
import app.news.demo.post.dto.PostResponse;
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
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostService postService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository, PostService postService, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postService = postService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(
            @RequestBody PostRequest postToCreate,
            Principal principal
            ) throws IllegalAccessException {
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        CategoryEntity category = categoryRepository.findBySlug(postToCreate.category());
        log.info("User - {}. Called createPost with content: {}", postToCreate, user);
        return ResponseEntity.ok(postService.createPost(postToCreate, user, category));
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAllPost(){
        log.info("Called getAllPost");
        List<PostResponse> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        log.info("Called getPost");
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequest postToUpdate, Principal principal) throws AccessDeniedException, IllegalAccessException {
        log.info("Called updatePost");
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(postService.updatePost(id, postToUpdate, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Principal principal) throws AccessDeniedException {
        log.info("Called deletePost");
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(postService.deletePost(id, user));
    }

}
