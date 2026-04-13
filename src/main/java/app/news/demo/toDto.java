package app.news.demo;

import app.news.demo.category.CategoryEntity;
import app.news.demo.category.dto.CategoryResponse;
import app.news.demo.comment.CommentEntity;
import app.news.demo.comment.CommentRepository;
import app.news.demo.comment.dto.CommentResponse;
import app.news.demo.post.PostEntity;
import app.news.demo.post.PostRepository;
import app.news.demo.post.dto.PostResponse;
import app.news.demo.user.UserEntity;
import app.news.demo.user.dto.UserResponse;

public class toDto {

    private static CommentRepository commentRepository;
    private static PostRepository postRepository;

    public toDto(CommentRepository commentRepository, PostRepository postRepository) {
        toDto.commentRepository = commentRepository;
        toDto.postRepository = postRepository;
    }

    public static PostResponse toResponsePost(PostEntity post) {
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
                commentRepository.findByPost(post).stream().map(toDto::toResponseComment).toList(),
                post.getCreateAt(),
                post.getUpdatedAt(),
                post.isPinned()
        );
    }
    public static CommentResponse toResponseComment(CommentEntity commentEntity){
        UserEntity author = commentEntity.getAuthor();
        ;
        UserResponse userResponse = new UserResponse(
                author.getId(),
                author.getUsername(),
                author.getEmail(),
                author.getRole()
        );
        return new CommentResponse(
                commentEntity.getContent(),
                userResponse,
                toResponsePost(commentEntity.getPost()),
                toResponseComment(commentEntity.getParent()),
                commentEntity.getReplies().stream().map(toDto::toResponseComment).toList(),
                commentEntity.getCreateAt(),
                commentEntity.getUpdateAt()
        );
    }
    public static CategoryResponse toResponseCategory(CategoryEntity categoryEntity) {
        return new CategoryResponse(categoryEntity.getName(), categoryEntity.getDescription());
    }
}
