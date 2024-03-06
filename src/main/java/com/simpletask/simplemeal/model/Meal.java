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
	
	private MealType typeMenu;
	
	private MealSize size;
	
	private double price;
	
	private String description;
	
	private boolean isSpecial;
	
	public Meal() {
	}
	
		

	public MealSize getSize() {
		return size;
	}



	public void setSize(MealSize size) {
		this.size = size;
	}



	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
