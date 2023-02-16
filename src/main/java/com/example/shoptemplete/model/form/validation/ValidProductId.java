package com.example.shoptemplete.model.form.validation;

import jakarta.validation.Constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductIdValidator.class)
public @interface ValidProductId {
    String message() default "No such project id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}