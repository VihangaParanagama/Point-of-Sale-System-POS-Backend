package com.ijse.database.service;

import com.ijse.database.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryEntity> getAllCategories();
    CategoryEntity findCategoryById(Long id);
    CategoryEntity createCategory(CategoryEntity categoryEntity);
    CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity);
    boolean deleteCategory(Long id);

}
