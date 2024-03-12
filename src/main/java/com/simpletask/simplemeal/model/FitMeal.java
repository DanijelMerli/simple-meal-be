package com.simpletask.simplemeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "fit_meals")
@PrimaryKeyJoinColumn(name = "id_fit")
public class FitMeal extends Meal {
    @Column(name = "price")
    private double price;

    @Column(name = "should_Order_Early")
    private boolean shouldOrderEarly;

    public FitMeal() {
    }

    public FitMeal(double price, boolean shouldOrderEarly) {
        this.price = price;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public FitMeal(int id, String name, String description, double price, boolean shouldOrderEarly) {
        super(id, name, description);
        this.price = price;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public FitMeal(String name, String description, double price, boolean shouldOrderEarly) {
        super(name, description);
        this.price = price;
        this.shouldOrderEarly = shouldOrderEarly;
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
