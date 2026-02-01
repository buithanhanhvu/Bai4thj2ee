package nhom6.Buithanhanhvu.services;

import nhom6.Buithanhanhvu.entities.Category;
import nhom6.Buithanhanhvu.repositories.ICategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
