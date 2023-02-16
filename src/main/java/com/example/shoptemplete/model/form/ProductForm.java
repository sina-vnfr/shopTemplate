package com.example.shoptemplete.model.form;

import lombok.Data;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductForm {
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0.1")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;
}
