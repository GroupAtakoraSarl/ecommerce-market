package com.atakora.ecommercemarket.service;

        import com.atakora.ecommercemarket.entities.Category;
        import com.atakora.ecommercemarket.entities.Product;
        import com.atakora.ecommercemarket.repository.ProductRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product newProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}
