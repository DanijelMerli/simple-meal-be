package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.RegularMeal;

public class RegularMealDTO {
    private int id;

    private String name;

    private String description;

    private double largePrice;

    private double smallPrice;

    public RegularMealDTO() {
    }

    public RegularMealDTO(int id, String name, String description, double largePrice, double smallPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
    }

    public RegularMealDTO(String name, String description, double largePrice, double smallPrice) {
        this.name = name;
        this.description = description;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
    }

    public RegularMealDTO(RegularMeal rm) {
        this.id = rm.getId();
        this.name = rm.getName();
        this.description = rm.getDescription();
        this.largePrice = rm.getLargePrice();
        this.smallPrice = rm.getSmallPrice();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
