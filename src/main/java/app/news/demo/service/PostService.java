package app.news.demo.service;

import app.news.demo.entity.*;
import app.news.demo.repository.CommentRepository;
import app.news.demo.repository.PostRepository;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private static PostRepository postRepository;
    private static CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public static Object createPost(PostResponse postResponse, UserEntity user) throws IllegalAccessException {
        if (postResponse.content() == null || postResponse.title() == null || postResponse.category() == null){
            throw new IllegalArgumentException("U miss any argument(content,title,category");
        }
        if (!user.isActive()){
            throw new IllegalAccessException("You are banned");
        }
        PostEntity post = new PostEntity(
                null,
                postResponse.title(),
                postResponse.content(),
                user,
                postResponse.category(),
                commentRepository.findByPost(postRepository.findByAuthor(user)),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false
        );
        postRepository.save(post);
        return (post);
    }
    public static List<PostEntity> getAllPost(){
        return postRepository.findByAuthorIsActive(true);
    }
    public PostResponse toResponsePost(PostEntity post) {
        UserEntity author = post.getAuthor();
        UserResponse userResponse = new UserResponse(
                author.getId(),
                author.getUsername(),
                author.getEmail(),
                author.getRole()
        );
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                userResponse,
                post.getCategory(),
                commentRepository.findByPost(post).stream().map(this::toResponseComment).toList(),
                post.getCreateAt(),
                post.getUpdatedAt(),
                post.isPinned()
        );
    }
    public CommentResponse toResponseComment(CommentEntity commentEntity){
        UserEntity author = commentEntity.getAuthor();
        UserResponse userResponse = new UserResponse(
                author.getId(),
                author.getUsername(),
                author.getEmail(),
                author.getRole()
        );
        return new CommentResponse(
                commentEntity.getId(),
                commentEntity.getContent(),
                userResponse,
                commentEntity.getCreateAt()
        );
    }
}
