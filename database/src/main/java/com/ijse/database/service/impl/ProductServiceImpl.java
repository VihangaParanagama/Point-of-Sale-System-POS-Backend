package com.ijse.database.service.impl;

import com.ijse.database.dto.ProductCountsDTO;
import com.ijse.database.dto.ProductDto;
import com.ijse.database.entity.CategoryEntity;
import com.ijse.database.entity.ProductEntity;
import com.ijse.database.repository.CategoryRepository;
import com.ijse.database.repository.ProductRepository;
import com.ijse.database.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductEntity createProduct(ProductDto productDto) {
        try {
            Long categoryId = productDto.getCategoryId();

            if (categoryId != null) {
                CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);

                if (productDto != null && categoryEntity != null) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setName(productDto.getName());
                    productEntity.setPrice(productDto.getPrice());
                    productEntity.setQty(productDto.getQty());
                    productEntity.setCategoryEntity(categoryEntity);

                    return productRepository.save(productEntity);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }



    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        try {
            ProductEntity existingProduct = productRepository.findById(id).orElse(null);

            if (existingProduct != null) {

                if (updatedProduct.getName() != null) {
                    existingProduct.setName(updatedProduct.getName());
                }

                if (updatedProduct.getCategoryEntity() != null) {
                    existingProduct.setCategoryEntity(updatedProduct.getCategoryEntity());
                }

                if (updatedProduct.getPrice() != null) {
                    existingProduct.setPrice(updatedProduct.getPrice());
                }

                if (updatedProduct.getQty() != null) {
                    existingProduct.setQty(updatedProduct.getQty());
                }

                return productRepository.save(existingProduct);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<ProductEntity> getProductByCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);

        if(categoryEntity != null){
            return productRepository.findProductByCategory(categoryEntity);
        }else{
            return null;
        }
    }

    @Override
    public String deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return "Product with ID " + id + " deleted successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete product with ID " + id + ".";
        }
    }

    @Override
    public ProductCountsDTO getProductCountsByCategoryAndStock() {

        Long countOfTotalProducts = productRepository.countAllProducts();
        Long countOutOfStock = productRepository.countOutOfStockProducts();
        Long countQtyLessThan10 = productRepository.countProductsQtyLessThan10();

        return new ProductCountsDTO(countOfTotalProducts, countOutOfStock, countQtyLessThan10);
    }
    

}
