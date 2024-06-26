package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderDTO;
import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.FitMeal;
import com.simpletask.simplemeal.model.Order;
import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        boolean isForToday = dto.isForToday();
        if (isForToday && currentHour >= 10) {
            throw new IllegalArgumentException("Cannot order for today after 10h");
        }
        List<OrderItem> orderItemList = orderItemService.convertDTOListToModelList(dto.getOrderItems(), userEmail);
        for (OrderItem item : orderItemList) {
            if ((item.getMeal() instanceof Extra) && (isForToday || (!isForToday && currentHour >= 17)))
                throw new IllegalArgumentException("Extras should be ordered a day early before 17h");
            if (item.getMeal() instanceof FitMeal) {
                FitMeal fit = (FitMeal) item.getMeal();
                if (fit.isShouldOrderEarly() && (isForToday || (!isForToday && currentHour >= 17)))
                    throw new IllegalArgumentException("Fit meal " + fit.getName() + " should be ordered a day early before 17h");
            }
        }
        double orderTotalPrice = calculateTotalPrice(orderItemList);
        Optional<Order> orderOpt = findByDate(dto.isForToday() ? getDate(new Date()) : getDate(getTomorrow()));
        Order order = orderOpt.orElseGet(() -> {
            Order newOrder = convertDTOtoModel(dto, userEmail);
            newOrder.setTotalPrice(0.0);
            newOrder.setOrderItems(new ArrayList<>());
            return addOrder(newOrder);
        });
        double newTotalPrice = 0.0;

        for (OrderItem item : orderItemList) {
            item.setOrder(order);
            this.orderItemService.addOrderItem(item);
        }
        order.setTotalPrice(order.getTotalPrice() + orderTotalPrice);
        orderRepository.saveAndFlush(order);
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

    public double calculateTotalPrice(List<OrderItem> items) {
        return items.stream().mapToDouble(item -> item.getMealCount()
                * mealService.calculatePriceByMeal(item.getMeal().getId(), item.getRegularMealSize())).sum();
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
