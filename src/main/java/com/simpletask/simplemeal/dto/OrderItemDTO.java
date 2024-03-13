package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.annotations.EnumValues;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public class OrderItemDTO {
    @NotNull(message = "Meal id cannot be empty")
    @Min(value = 1, message = "invalid meal id")
    private int mealId;

    @EnumValues(values = {"LARGE", "SMALL"}, message = "Invalid meal size; available: LARGE & SMALL", nullable = true)
    @Nullable
    private String mealSize;

    @NotNull(message = "Meal count cannot be null")
    @Min(value = 1, message = "You should order at least one meal")
    private int mealCount;

    public OrderItemDTO() {
    }

    public OrderItemDTO(int mealId, String mealSize, int mealCount) {
        this.mealId = mealId;
        this.mealSize = mealSize;
        this.mealCount = mealCount;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealSize() {
        return mealSize;
    }

    public void setMealSize(String mealSize) {
        this.mealSize = mealSize;
    }

    public int getMealCount() {
        return mealCount;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "mealId=" + mealId +
                ", mealSize='" + mealSize + '\'' +
                ", mealCount=" + mealCount +
                '}';
    }
}
