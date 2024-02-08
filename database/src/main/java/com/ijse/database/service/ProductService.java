package com.ijse.database.service;

import com.ijse.database.dto.ProductCountsDTO;
import com.ijse.database.dto.ProductDto;
import com.ijse.database.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity createProduct(ProductDto productDto);
    ProductEntity updateProduct(Long id, ProductEntity productEntity);
    List<ProductEntity> getProductByCategory(Long id);
    String  deleteProduct(Long id);
    ProductCountsDTO getProductCountsByCategoryAndStock();

}
