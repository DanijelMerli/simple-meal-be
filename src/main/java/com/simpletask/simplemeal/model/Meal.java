package com.simpletask.simplemeal.model;

import java.io.Serializable;

import com.simpletask.simplemeal.enums.MealSize;
import com.simpletask.simplemeal.enums.MealType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Meal implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFoodMenu;
	
	private String name;
	
	private MealType typeMenu;
	
	private double largePrice;
	
	private double smallPrice;
	
	private String description;
	
	private boolean shouldOrderEarly;
	
	
	public Meal() {
	}
	
	
	
		

	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}

	
	

	public int getIdFoodMenu() {
		return idFoodMenu;
	}

	public void setIdFoodMenu(int idFoodMenu) {
		this.idFoodMenu = idFoodMenu;
	}

	public MealType getTypeMenu() {
		return typeMenu;
	}

	public void setTypeMenu(MealType typeMenu) {
		this.typeMenu = typeMenu;
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





	public boolean isShouldOrderEarly() {
		return shouldOrderEarly;
	}





	public void setShouldOrderEarly(boolean shouldOrderEarly) {
		this.shouldOrderEarly = shouldOrderEarly;
	}
	
	
	
}
