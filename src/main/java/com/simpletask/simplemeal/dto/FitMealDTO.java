package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.FitMeal;

public class FitMealDTO {
    private int id;

    private String name;

    private String description;

    private double price;

    private boolean shouldOrderEarly;

    public FitMealDTO() {
    }

    public FitMealDTO(int id, String name, String description, double price, boolean shouldOrderEarly) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public FitMealDTO(String name, String description, double price, boolean shouldOrderEarly) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public FitMealDTO(FitMeal fm) {
        this.id = fm.getId();
        this.name = fm.getName();
        this.description = fm.getDescription();
        this.price = fm.getPrice();
        this.shouldOrderEarly = fm.isShouldOrderEarly();
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
