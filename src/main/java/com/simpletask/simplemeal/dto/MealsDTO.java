package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.Meal;

public class MealsDTO {
	
	private int idFoodMenu;
	
	private String name;
	
	private String typeMenu;
	
	private String size;
	

	
	private double largePrice;
	
	private double smallPrice;
	
	
	private String description;
	
	private boolean shouldOrderEarly;

	
	
	
	public MealsDTO(int idFoodMenu, String name, String typeMenu, String size, double largePrice, double smallPrice,
			String description, boolean shouldOrderEarly) {
		super();
		this.idFoodMenu = idFoodMenu;
		this.name = name;
		this.typeMenu = typeMenu;
		this.size = size;
		this.largePrice = largePrice;
		this.smallPrice = smallPrice;
		this.description = description;
		this.shouldOrderEarly = shouldOrderEarly;
	}




	public MealsDTO() {
		
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

	public String getTypeMenu() {
		return typeMenu;
	}

	public void setTypeMenu(String typeMenu) {
		this.typeMenu = typeMenu;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
	
	public MealsDTO(Meal meal) {
		this.idFoodMenu = meal.getIdFoodMenu();
		this.name = meal.getName();
		this.typeMenu = meal.getTypeMenu().toString();
		this.largePrice = meal.getLargePrice();
		this.smallPrice = meal.getSmallPrice();
		this.description = meal.getDescription();
		this.shouldOrderEarly = meal.isShouldOrderEarly();
	}

	
	

}
