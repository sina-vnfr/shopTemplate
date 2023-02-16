package com.example.shoptemplete.repository;


import com.example.shoptemplete.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
