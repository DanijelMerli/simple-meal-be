package com.simpletask.simplemeal.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.ChecksListUserDTO;
import com.simpletask.simplemeal.dto.ChosenOneDTO;
import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.model.Order;
import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.OrderItemRepository;
import com.simpletask.simplemeal.repository.OrderRepository;
import com.simpletask.simplemeal.repository.UserRepository;

@Service
public class ChosenOneService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	DailyMenuService dailyMenuSerivce;

	@Autowired
	private IMealService mealService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserRepository userRepo;

	public ChosenOneDTO getAllUsers() {
		Date today = dailyMenuSerivce.getToday();
		ChosenOneDTO chosenOneDTO = null;
		Optional<Order> orderForToday = orderRepo.findByDate(today);
		if (orderForToday.isEmpty()) {
			return null;
		}
		List<OrderItem> orderItemsForToday = orderItemRepository.findByOrderDate(today);
		List<ChecksListUserDTO> checksList = orderItemsForToday.stream().collect(
				Collectors.groupingBy(OrderItem::getOrderer, Collectors.summingDouble(this::calculateItemPrice)))
				.entrySet().stream().map(entry -> {
					ChecksListUserDTO dto = new ChecksListUserDTO();
					dto.setFirstName(entry.getKey().getFirstName());
					dto.setLastName(entry.getKey().getLastName());
					dto.setEmail(entry.getKey().getEmail());
					dto.setPriceForOrder(entry.getValue());
					dto.setPaid(getIsPaid(entry.getKey().getId()));
					return dto;
				}).collect(Collectors.toList());
		chosenOneDTO = new ChosenOneDTO(checksList, orderForToday.get().getTotalPrice());
		return chosenOneDTO;
	}
	
	private double calculateItemPrice(OrderItem orderItem) {
		MealSize mealSize = orderItem.getRegularMealSize();
		int mealCount = orderItem.getMealCount();
		return mealCount * mealService.calculatePriceByMeal(orderItem.getMeal().getId(), mealSize);
	}

	public boolean markOrderAsPaid(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		Date today = dailyMenuSerivce.getToday();
		if (user.isEmpty()) {
			return false;
		}
		List<OrderItem> listOfOrderItems = orderItemRepository.findByOrdererAndOrderDate(user.get(), today);
		if (listOfOrderItems.isEmpty()) {
			return false;
		} else {
			for (OrderItem orderItem : listOfOrderItems) {
				orderItem.setPaid(true);
			}
		}
		orderItemRepository.saveAll(listOfOrderItems);
		return true;
	}
	
	private boolean getIsPaid(Integer userId) {
		List<OrderItem> orderItems = orderItemRepository.findByOrderer(userRepo.findById(userId).orElse(null));
		for (OrderItem orderItem : orderItems) {
			if (orderItem.isPaid() == false) {
				return false;
			}
		}
		return true;
	}
}
