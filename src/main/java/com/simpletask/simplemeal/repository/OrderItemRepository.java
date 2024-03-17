package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
