package com.simpletask.simplemeal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simpletask.simplemeal.enums.MealSize;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderer")
    private User orderer;

    @ManyToOne
    @JoinColumn(name = "meal")
    private Meal meal;

    @Column(name = "regular_Meal_Size")
    private MealSize regularMealSize;

    @Column(name = "meal_count")
    private int mealCount;
    
    @Column(name = "paid")
    private boolean paid;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "from_order", nullable=false)
    private Order order;

    public OrderItem() {
    }

    public OrderItem(int id, User user, Meal meal, MealSize regularMealSize, Order order, int mealCount) {
        this.id = id;
        this.orderer = user;
        this.meal = meal;
        this.regularMealSize = regularMealSize;
        this.order = order;
        this.mealCount = mealCount;
    }

    public OrderItem(User user, Meal meal, MealSize regularMealSize, Order order, int mealCount) {
        this.orderer = user;
        this.meal = meal;
        this.regularMealSize = regularMealSize;
        this.order = order;
        this.mealCount = mealCount;
    }
    
    
    public OrderItem(int id, User orderer, Meal meal, MealSize regularMealSize, int mealCount, boolean paid,
			Order order) {
		super();
		this.id = id;
		this.orderer = orderer;
		this.meal = meal;
		this.regularMealSize = regularMealSize;
		this.mealCount = mealCount;
		this.paid = paid;
		this.order = order;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOrderer() {
        return orderer;
    }

    public void setOrderer(User user) {
        this.orderer = user;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public MealSize getRegularMealSize() {
        return regularMealSize;
    }

    public void setRegularMealSize(MealSize regularMealSize) {
        this.regularMealSize = regularMealSize;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getMealCount() {
        return mealCount;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
    
}
