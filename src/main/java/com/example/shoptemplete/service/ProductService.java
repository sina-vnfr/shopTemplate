package com.example.shoptemplete.service;


import com.example.shoptemplete.model.entity.Product;
import com.example.shoptemplete.model.form.ProductForm;
import com.example.shoptemplete.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_COMMITTED)
public class ProductService {
    private final ProductRepository productRepository;

    public Long createProduct(ProductForm productForm) {
        Product product = Product.builder()
                .name(productForm.getName())
                .price(productForm.getPrice())
                .build();
        productRepository.save(product);
        return product.getProductId();
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public void updateProduct(Product product, ProductForm productForm) {
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        productRepository.save(product);
    }
}
