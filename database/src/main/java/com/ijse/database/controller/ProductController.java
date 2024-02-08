package com.ijse.database.controller;

import com.ijse.database.dto.ProductCountsDTO;
import com.ijse.database.dto.ProductDto;
import com.ijse.database.entity.ProductEntity;
import com.ijse.database.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity <?> createProduct(@RequestBody ProductDto productDto){
        try {
            return ResponseEntity.status(201).body(productService.createProduct(productDto));
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.status(400).body("Failed to create the Product");
        }

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        ProductEntity productEntity = productService.getProductById(id);

        if(productEntity != null) {
            return ResponseEntity.status(200).body(productEntity);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/products/{id}")
    public ProductEntity updateProduct(@PathVariable Long id,@RequestBody ProductEntity productEntity){
        return productService.updateProduct(id,productEntity);
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<ProductEntity>> getProductByCategory(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.getProductByCategory(id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            String deletionResult = productService.deleteProduct(id);

            if (deletionResult.contains("successfully")) {
                return ResponseEntity.status(204).body(deletionResult);
            } else {
                return ResponseEntity.status(404).body(deletionResult);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(500).body("Failed to delete the product");
        }
    }

    @GetMapping("/counts")
    public ResponseEntity<ProductCountsDTO> getProductCounts() {
        try {
            ProductCountsDTO productCountsDTO = productService.getProductCountsByCategoryAndStock();
            return new ResponseEntity<>(productCountsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
