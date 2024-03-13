package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderDTO;

public interface IOrderService {
    void addOrder(OrderDTO dto, String userEmail);
}
