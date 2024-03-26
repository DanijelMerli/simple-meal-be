package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderDate(Date currentDate);
}
