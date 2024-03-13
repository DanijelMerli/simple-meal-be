package com.simpletask.simplemeal.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderDTO {
    @Valid
    @NotNull(message = "Item list cannot be empty")
    @Size(min = 1, message = "Item list cannot be empty")
    private List<OrderItemDTO> orderItems;

    @NotNull(message = "Order should have a time") // ne radi nista pametno jer se null evaluira u false :-D
    private boolean forToday;

    public OrderDTO() {
    }

    public OrderDTO(List<OrderItemDTO> orderItems, boolean forToday) {
        this.orderItems = orderItems;
        this.forToday = forToday;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public boolean isForToday() {
        return forToday;
    }

    public void setForToday(boolean forToday) {
        this.forToday = forToday;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "forToday=" + forToday +
                ", orderItems=" + orderItems.toString() +
                '}';
    }
}
