package com.example.shoptemplete.model.form;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderForm {
    @Email
    private String buyersEmail;
    @NotEmpty
    private List<@Valid OrderItemForm> items;
}
