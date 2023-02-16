package com.example.shoptemplete.service;


import com.example.shoptemplete.model.entity.Order;
import com.example.shoptemplete.model.entity.OrderItem;
import com.example.shoptemplete.model.entity.Product;
import com.example.shoptemplete.model.form.OrderForm;
import com.example.shoptemplete.model.form.OrderItemForm;
import com.example.shoptemplete.repository.OrderRepository;
import com.example.shoptemplete.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_COMMITTED)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Long createOrder(OrderForm orderForm) {
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .buyersEmail(orderForm.getBuyersEmail())
                .build();
        List<OrderItem> orderItems = toOrderItems(order, orderForm.getItems());
        order.setItems(orderItems);
        orderRepository.save(order);
        return order.getOrderId();
    }

    public Optional<Order> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findByOrderDate(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDate(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    private List<OrderItem> toOrderItems(Order order, List<OrderItemForm> orderItems) {
        return orderItems.stream()
                .map(oi -> {
                    return OrderItem.builder()
                            .amount(oi.getAmount())
                            .productId(oi.getProductId())
                            .price(productRepository.findById(oi.getProductId()).map(Product::getPrice).orElse(BigDecimal.ZERO))
                            .order(order)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
