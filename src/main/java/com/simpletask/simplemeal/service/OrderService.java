package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderDTO;
import com.simpletask.simplemeal.model.Order;
import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<OrderItem> orderItemList = orderItemService.convertDTOListToModelList(dto.getOrderItems(), userEmail);
        Optional<Order> orderOpt = findByDate(dto.isForToday() ? getDate(new Date()) : getDate(getTomorrow()));
        Order order = orderOpt.orElseGet(() -> {
            Order newOrder = convertDTOtoModel(dto, userEmail);
            newOrder.setTotalPrice(calculateTotalPrice(orderItemList));
            newOrder.setOrderItems(new ArrayList<>());
            return addOrder(newOrder);
        });
        for (OrderItem item : orderItemList) {
            item.setOrder(order);
            this.orderItemService.addOrderItem(item);
        }
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public Order convertDTOtoModel(OrderDTO dto, String userEmail) {
        Order order = new Order();
        order.setDate(dto.isForToday() ? getDate(new Date()) : getDate(getTomorrow()));
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

    @Override
    public Optional<Order> findByDate(Date date) {
        return orderRepository.findByDate(date);
    }

    private Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
