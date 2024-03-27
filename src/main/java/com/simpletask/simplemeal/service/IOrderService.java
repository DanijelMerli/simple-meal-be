package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderDTO;
import com.simpletask.simplemeal.model.Order;

import java.util.Date;
import java.util.Optional;

public interface IOrderService {
	void addOrder(OrderDTO dto, String userEmail);

	Optional<Order> findByDate(Date date);

	Order addOrder(Order order);

	Order convertDTOtoModel(OrderDTO dto, String userEmail);
}
