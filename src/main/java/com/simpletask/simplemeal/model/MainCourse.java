package com.simpletask.simplemeal.model;

import com.simpletask.simplemeal.enums.MealType;
import jakarta.persistence.*;

@Entity
@Table(name = "main_courses")
@PrimaryKeyJoinColumn(name = "main_course_id")
public class MainCourse extends Meal {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "main_course_id", nullable = false)
//    private int id;

    @Column(name = "type_menu")
    private MealType typeMenu;

    @Column(name = "large_price")
    private double largePrice;

    @Column(name = "small_price")
    private double smallPrice;

    @Column(name = "should_Order_Early")
    private boolean shouldOrderEarly;

    public MainCourse() {

    }

    public MainCourse(MealType typeMenu, double largePrice, double smallPrice, boolean shouldOrderEarly) {
        this.typeMenu = typeMenu;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public MainCourse(int id, String name, String description, MealType typeMenu, double largePrice, double smallPrice, boolean shouldOrderEarly) {
        super(id, name, description);
//        this.id = id;
        this.typeMenu = typeMenu;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
        this.shouldOrderEarly = shouldOrderEarly;
    }

    public MainCourse(String name, String description, MealType typeMenu, double largePrice, double smallPrice, boolean shouldOrderEarly) {
        super(name, description);
        this.typeMenu = typeMenu;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
        this.shouldOrderEarly = shouldOrderEarly;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public MealType getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(MealType typeMenu) {
        this.typeMenu = typeMenu;
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
