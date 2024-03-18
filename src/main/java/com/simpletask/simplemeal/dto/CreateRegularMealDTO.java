package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.RegularMeal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateRegularMealDTO {
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Large price should be a positive number")
    private double largePrice;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Small price should be a positive number")
    private double smallPrice;

    public CreateRegularMealDTO() {
    }

    public CreateRegularMealDTO(String name, String description, double largePrice, double smallPrice) {
        this.name = name;
        this.description = description;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
    }

    public CreateRegularMealDTO(RegularMeal rm) {
        this.name = rm.getName();
        this.description = rm.getDescription();
        this.largePrice = rm.getLargePrice();
        this.smallPrice = rm.getSmallPrice();
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

    public double getLargePrice() {
        return largePrice;
    }

    public void setLargePrice(double largePrice) {
        this.largePrice = largePrice;
    }

    public double getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(double smallPrice) {
        this.smallPrice = smallPrice;
    }
}
