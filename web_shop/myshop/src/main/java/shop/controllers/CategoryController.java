package shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.dto.category.CategoryCreateDTO;
import shop.dto.category.CategoryItemDTO;
import shop.dto.category.CategoryUpdateDTO;
import shop.entities.CategoryEntity;
import shop.mapper.CategoryMapper;
import shop.repositories.CategoryRepository;
import shop.storage.StorageService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index() {
        var list = categoryRepository.findAll();
        var model = categoryMapper.CategoryItemsByCategories(list);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CategoryEntity> create(CategoryCreateDTO model) {
        var fileName = storageService.save(model.getBase64());
        CategoryEntity category = categoryMapper.CategoryByCreateCategoryDTO(model);
        category.setImage(fileName);
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8085/api/categories/1
    @GetMapping("{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable("id") Integer categoryId){
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(categoryId);
        return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
    }
    // Build Update User REST API
    @PutMapping("{id}")
    // http://localhost:8085/api/categories/1
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable("id") Integer categoryId,
                                           @RequestBody CategoryUpdateDTO model){
        var existingCategory = categoryRepository.findById(categoryId).get();
        existingCategory.setName(model.getName());
        CategoryEntity updatedCategory = categoryRepository.save(existingCategory);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer categoryId){
        categoryRepository.deleteById(categoryId);
        return new ResponseEntity<>("Category successfully deleted!", HttpStatus.OK);
    }
}
