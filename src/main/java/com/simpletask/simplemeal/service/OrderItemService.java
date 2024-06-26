package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderItemDTO;
import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.MealRepository;
import com.simpletask.simplemeal.repository.OrderItemRepository;
import com.simpletask.simplemeal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderItem convertDTOtoModel(OrderItemDTO dto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Meal meal = mealRepository.findById(dto.getMealId())
                .orElseThrow(() -> new NotFoundException("Meal not found"));
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderer(user);
        orderItem.setMeal(meal);
        orderItem.setMealCount(dto.getMealCount());
        orderItem.setRegularMealSize(dto.getMealSize() != null ? MealSize.valueOf(dto.getMealSize()) : MealSize.NONE);
        return orderItem;
    }

    @Override
    public List<OrderItem> convertDTOListToModelList(List<OrderItemDTO> dtoList, String userEmail) {
        return dtoList.stream()
                .map(dto -> convertDTOtoModel(dto, userEmail))
                .collect(Collectors.toList());
    }

    @Override
    public void addOrderItem(OrderItem item) {
        item = orderItemRepository.saveAndFlush(item);
    }

    public List<User> getUsersWhoOrderedToday(Date currentDate) {
        List<OrderItem> orderItemsForToday = orderItemRepository.findByOrderDate(currentDate);

        List<User> users = orderItemsForToday.stream()
                .map(OrderItem::getOrderer)
                .distinct()
                .collect(Collectors.toList());
        return users;
    }
}
