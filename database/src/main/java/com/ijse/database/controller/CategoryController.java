package com.ijse.database.controller;

import com.ijse.database.entity.CategoryEntity;
import com.ijse.database.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    public CategoryEntity createCategory(@RequestBody CategoryEntity categoryEntity){
        return categoryService.createCategory(categoryEntity);
    }

    @GetMapping("/categories/{id}")
    public CategoryEntity getCategoryById(@PathVariable Long id){
        return categoryService.findCategoryById(id);
    }

    @PutMapping("/categories/{id}")
    public CategoryEntity updateCategory(@PathVariable Long id ,@RequestBody CategoryEntity categoryEntity){
        return categoryService.updateCategory(id,categoryEntity);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategory(id);

        if (isDeleted) {
            return "Category deleted successfully";
        } else {
            return "Category not found or unable to delete";
        }
    }
}
