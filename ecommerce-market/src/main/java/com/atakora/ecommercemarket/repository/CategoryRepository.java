package com.atakora.ecommercemarket.repository;

import com.atakora.ecommercemarket.entities.Category;
import com.atakora.ecommercemarket.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);
    List<Product> findProductsById(Integer categoryId);
}

