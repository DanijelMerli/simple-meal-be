package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderDTO;
import com.simpletask.simplemeal.model.Meal;
import com.simpletask.simplemeal.model.Order;
import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IMealService mealService;

    @Override
    public void addOrder(OrderDTO dto, String userEmail) {
        Order order = convertDTOtoModel(dto, userEmail);
        order.setOrderItems(new ArrayList<>());
        order = orderRepository.saveAndFlush(order);
        List<OrderItem> orderItemList = orderItemService.convertDTOListToModelList(dto.getOrderItems(), userEmail);
        for (OrderItem item : orderItemList) {
            item.setOrder(order);
            this.orderItemService.addOrderItem(item);
        }
    }

    public Order convertDTOtoModel(OrderDTO dto, String userEmail) {
        Order order = new Order();
        List<OrderItem> orderItemList = orderItemService.convertDTOListToModelList(dto.getOrderItems(), userEmail);
        order.setOrderItems(orderItemList);
        order.setTotalPrice(calculateTotalPrice(orderItemList));
        order.setDate(dto.isForToday() ? new Date() : getTomorrow());
        return order;
    }

    private double calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getMealCount() * mealService.calculatePriceByMeal(item.getMeal().getId(), item.getRegularMealSize()))
                .sum();
    }

    private Date getTomorrow() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

}
