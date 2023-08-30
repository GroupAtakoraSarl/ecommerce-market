package com.atakora.ecommercemarket.controller;


import com.atakora.ecommercemarket.common.ApiResponse;
import com.atakora.ecommercemarket.entities.Category;
import com.atakora.ecommercemarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }
    @GetMapping("/liste")
    public List<Category> listeCategory(){
        return categoryService.listeCategory();
    }
}
