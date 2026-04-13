package app.news.demo.post;

import app.news.demo.category.CategoryEntity;
import app.news.demo.category.CategoryRepository;
import app.news.demo.post.dto.PostRequest;
import app.news.demo.post.dto.PostResponse;
import app.news.demo.toDto;
import app.news.demo.user.UserRoles;
import app.news.demo.user.dto.UserResponse;
import app.news.demo.comment.CommentRepository;
import app.news.demo.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.news.demo.toDto.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.categoryRepository = categoryRepository;
    }

    public Object createPost(PostRequest postResponse, UserEntity user, CategoryEntity categoryEntity) throws IllegalAccessException {
        if (postResponse.content() == null || postResponse.title() == null || postResponse.category() == null){
            throw new IllegalArgumentException("U miss any argument(content,title,category");
        }
        if (!user.isActive()){
            throw new IllegalAccessException("You are banned");
        }
        PostEntity post = new PostEntity(
                null,
                postResponse.title(),
                postResponse.title().toLowerCase(Locale.ROOT),
                PostStatus.DRAFT,
                0L,
                postResponse.content(),
                user,
                categoryEntity,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false
        );
        postRepository.save(post);
        return (post);
    }
    public List<PostResponse> getAllPost(){
        List<PostEntity> allPost = postRepository.findByAuthorIsActive(true);
        return allPost.stream().map(this::toAllResponsePost).toList();
    }

    public PostResponse toAllResponsePost(PostEntity post){
        UserResponse author = UserResponse.from(post.getAuthor());
        String content = post.getContent().length() > 50
                ? post.getContent().substring(0, 50) + "..."
                : post.getContent();
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                content,
                author,
                post.getCategory(),
                commentRepository.findByPost(post).stream().map(toDto::toResponseComment).toList(),
                post.getCreateAt(),
                post.getUpdatedAt(),
                post.isPinned()
        );
    }


    @Transactional
    public PostResponse getPost(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if (!post.getAuthor().isActive()){
            throw new IllegalArgumentException("Author is banned");
        }
        postRepository.incrementViewCount(post.getId());
        return toDto.toResponsePost(post);
    }

    @Transactional
    public Object updatePost(Long id, PostRequest postToUpdate, UserEntity user) throws IllegalAccessException, AccessDeniedException {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if (post.getAuthor() != user || user.getRole() != UserRoles.ADMIN){
            throw new AccessDeniedException("You can`t update this post");
        }
        if (postToUpdate.title() != null){post.setTitle(postToUpdate.title());}
        if (postToUpdate.content() != null){post.setContent(postToUpdate.content());}
        if (postToUpdate.category() != null) {
            if (categoryRepository.existsByName(postToUpdate.category())) {
                post.setCategory(categoryRepository.findBySlug(postToUpdate.category().toLowerCase()));
            }
            throw new IllegalArgumentException("Category not found");
        }
        post.setStatus(postToUpdate.status());
        postRepository.save(post);
        return toAllResponsePost(post);
    }

    public Object deletePost(Long id, UserEntity user) throws AccessDeniedException {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if (post.getAuthor() != user || user.getRole() != UserRoles.ADMIN){
            throw new AccessDeniedException("You can`t delete this post");
        }
        post.setStatus(PostStatus.DELETED);
        postRepository.save(post);
        return "Successfully deleted this post";
    }
}
