package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.annotations.EnumValues;
import com.simpletask.simplemeal.model.Extra;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateExtraDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Extra Type cannot be null")
    @EnumValues(values = {"DESSERT", "SOUP"}, message = "Invalid extra type; available: DESSERT & SOUP", nullable = false)
    private String extraType;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price should be a positive number")
    private double price;

    public CreateExtraDTO() {
    }

    public CreateExtraDTO(String name, String description, String extraType, double price) {
        this.name = name;
        this.description = description;
        this.extraType = extraType;
        this.price = price;
    }

    public CreateExtraDTO(Extra extra) {
        this.extraType = extra.getExtraType().toString();
        this.name = extra.getName();
        this.description = extra.getDescription();
        this.price = extra.getPrice();
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

    public String getExtraType() {
        return extraType;
    }

    public void setExtraType(String extraType) {
        this.extraType = extraType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
