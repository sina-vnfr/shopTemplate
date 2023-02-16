package com.example.shoptemplete.model.form.validation;


import com.example.shoptemplete.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ProductIdValidator implements ConstraintValidator<ValidProductId, Long> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.findById(id).isPresent();
    }
}