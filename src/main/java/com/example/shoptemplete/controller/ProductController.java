package com.example.shoptemplete.controller;


import com.example.shoptemplete.model.entity.Product;
import com.example.shoptemplete.model.form.ProductForm;
import com.example.shoptemplete.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return productService.findById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@Validated @RequestBody ProductForm productForm) {
        return ResponseEntity.ok(productService.createProduct(productForm));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @Validated @RequestBody ProductForm productForm) {
        return ResponseEntity.status(
                        productService.findById(productId)
                                .map(product -> {
                                    productService.updateProduct(product, productForm);
                                    return HttpStatus.OK;
                                }).orElse(HttpStatus.NOT_FOUND))
                .build();
    }
}
