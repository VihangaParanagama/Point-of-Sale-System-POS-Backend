package com.ijse.database.service.impl;

import com.ijse.database.entity.CategoryEntity;
import com.ijse.database.entity.ProductEntity;
import com.ijse.database.repository.CategoryRepository;
import com.ijse.database.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity) {
        CategoryEntity existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            existingCategory.setName(categoryEntity.getName());
            return categoryRepository.save(existingCategory);
        } else {
            return null;
        }
    }
    @Override
    public boolean deleteCategory(Long id) {
        CategoryEntity category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            List<ProductEntity> products = category.getProductEntities();
            if (products.isEmpty()) {
                categoryRepository.delete(category);
                return true;
            } else {

                return false;
            }
        } else {

            return false;
        }
    }

}
