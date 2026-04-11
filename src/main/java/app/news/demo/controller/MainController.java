package app.news.demo.controller;

import app.news.demo.entity.UserEntity;
import app.news.demo.entity.UserResponse;
import app.news.demo.repository.PostRepository;
import app.news.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    public MainController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> userAccess(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserEntity user = userRepository.findUserByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        ));
    }
}
