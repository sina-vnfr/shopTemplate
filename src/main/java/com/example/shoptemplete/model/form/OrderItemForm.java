package com.example.shoptemplete.model.form;


import com.example.shoptemplete.model.form.validation.ValidProductId;
import lombok.Data;

import jakarta.validation.constraints.Min;

@Data
public class OrderItemForm {
    @ValidProductId
    private Long productId;
    @Min(1)
    private Integer amount;
}
