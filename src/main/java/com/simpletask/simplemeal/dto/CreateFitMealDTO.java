package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.FitMeal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateFitMealDTO {
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price should be a positive number")
    private double price;

    @NotNull(message = "Should Order Early Field should not be null")
    private boolean shouldOrderEarly;

    public CreateFitMealDTO() {
    }

    public CreateFitMealDTO(String name, String description, double price, boolean shouldOrderEarly) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public CreateFitMealDTO(FitMeal fm) {
        this.name = fm.getName();
        this.description = fm.getDescription();
        this.price = fm.getPrice();
        this.shouldOrderEarly = fm.isShouldOrderEarly();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isShouldOrderEarly() {
        return shouldOrderEarly;
    }

    public void setShouldOrderEarly(boolean shouldOrderEarly) {
        this.shouldOrderEarly = shouldOrderEarly;
    }
}
