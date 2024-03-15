package com.simpletask.simplemeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "regular_meals")
@PrimaryKeyJoinColumn(name = "id_regular")
public class RegularMeal extends Meal {

    @Column(name = "large_price")
    private double largePrice;

    @Column(name = "small_price")
    private double smallPrice;

    public RegularMeal() {
    }

    public RegularMeal(double largePrice, double smallPrice) {
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
    }

    public RegularMeal(int id, String name, String description, double largePrice, double smallPrice) {
        super(id, name, description);
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
    }

    public RegularMeal(String name, String description, double largePrice, double smallPrice) {
        super(name, description);
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
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
