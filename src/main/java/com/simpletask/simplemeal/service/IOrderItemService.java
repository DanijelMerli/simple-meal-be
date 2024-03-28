package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.dto.OrderItemDTO;
import com.simpletask.simplemeal.model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    OrderItem convertDTOtoModel(OrderItemDTO dto, String userEmail);

    List<OrderItem> convertDTOListToModelList(List<OrderItemDTO> dtoList, String userEmail);

    void addOrderItem(OrderItem item);
}
