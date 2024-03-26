package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.model.User;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	List<OrderItem> findByOrderDate(Date currentDate);
	List<OrderItem> findByOrdererAndOrderDate(User user, Date date);
}
