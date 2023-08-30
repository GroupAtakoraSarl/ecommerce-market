package com.atakora.ecommercemarket.service;


import com.atakora.ecommercemarket.entities.Category;
import com.atakora.ecommercemarket.entities.Product;
import com.atakora.ecommercemarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
    public List<Category> listeCategory(){
        return categoryRepository.findAll();
    }

    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return categoryRepository.findProductsById(categoryId);
    }

    public Category getCategoryById(Integer categoryId) {
        return null;
    }
}