package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.enums.MealType;
import com.simpletask.simplemeal.model.MainCourse;

public class MainCourseDTO {
    private int id;

    private String name;

    private String description;

    private String typeMenu;

    private double largePrice;

    private double smallPrice;

    private boolean shouldOrderEarly;

    public MainCourseDTO() {
    }

    public MainCourseDTO(int id, String name, String description, MealType typeMenu, double largePrice, double smallPrice, boolean shouldOrderEarly) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeMenu = typeMenu.toString();
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public MainCourseDTO(String name, String description, MealType typeMenu, double largePrice, double smallPrice, boolean shouldOrderEarly) {
        this.name = name;
        this.description = description;
        this.typeMenu = typeMenu.toString();
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public MainCourseDTO(MainCourse mc) {
        this.id = mc.getId();
        this.name=mc.getName();
        this.description = mc.getDescription();
        this.typeMenu = mc.getTypeMenu().toString();
        this.largePrice = mc.getLargePrice();
        this.smallPrice = mc.getSmallPrice();
        this.shouldOrderEarly = mc.isShouldOrderEarly();
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

    public String getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(MealType typeMenu) {
        this.typeMenu = typeMenu.toString();
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

    public boolean isShouldOrderEarly() {
        return shouldOrderEarly;
    }

    public void setShouldOrderEarly(boolean shouldOrderEarly) {
        this.shouldOrderEarly = shouldOrderEarly;
    }
}
