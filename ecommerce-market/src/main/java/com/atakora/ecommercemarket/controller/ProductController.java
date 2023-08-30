package com.atakora.ecommercemarket.controller;


import com.atakora.ecommercemarket.entities.Category;
import com.atakora.ecommercemarket.entities.Product;
import com.atakora.ecommercemarket.exception.ProductNotFoundException;
import com.atakora.ecommercemarket.repository.ProductRepository;
import com.atakora.ecommercemarket.service.CategoryService;
import com.atakora.ecommercemarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostMapping("/product")
    Product newProduct(@RequestBody Product newProduct){
        return productRepository.save(newProduct);
    }
    @RequestMapping("/products")
    List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
    }
    @PutMapping("/product/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return productRepository.findById(id)
                .map(product -> {
                    product.setNom(newProduct.getNom());
                    product.setPrix(newProduct.getPrix());
                    product.setDescription(newProduct.getDescription());
                    product.setAvailable(newProduct.isAvailable());
                    product.setImage(newProduct.getImage());
                    return productRepository.save(product);
                }).orElseThrow(()->new ProductNotFoundException(id));
    }
    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable Long id) {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return "user with the "+id+" has been delete correctly";
    }

    @GetMapping("/by-category/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable Integer categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category != null) {
            ProductService productService = null;
            return productService.getProductsByCategory(category);
        }
        return Collections.emptyList(); // Retourner une liste vide si la catégorie n'existe pas
    }
}
