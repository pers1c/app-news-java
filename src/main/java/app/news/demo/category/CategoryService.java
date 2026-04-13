package app.news.demo.category;

import app.news.demo.category.dto.CategoryRequest;
import app.news.demo.category.dto.CategoryResponse;
import app.news.demo.toDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = new CategoryEntity(
                null,
                categoryRequest.name(),
                categoryRequest.name().toLowerCase(),
                categoryRequest.description(),
                LocalDateTime.now()
        );
        categoryRepository.save(categoryEntity);
        return new CategoryResponse(categoryRequest.name(), categoryRequest.description());
    }

    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> allCategories = categoryRepository.findAll();
        return allCategories.stream().map(toDto::toResponseCategory).toList();
    }
}
