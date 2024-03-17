package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByDate(Date date);
}
