package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.Meal;

public class MealDTO {
	
	private int id;
	
	private String name;

	private String description;

	
	
	
	public MealDTO(int idFoodMenu, String name, String description) {
		super();
		this.id = idFoodMenu;
		this.name = name;
		this.description = description;
	}




	public MealDTO() {
		
	}
	
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MealDTO(Meal meal) {
		this.id = meal.getId();
		this.name = meal.getName();
		this.description = meal.getDescription();
	}

	
	

}
