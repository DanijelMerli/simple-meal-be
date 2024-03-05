package com.simpletask.simplemeal.dto;

public class ListOfMealsDTO {
	
private int idFoodMenu;
	
	private String typeMenu;
	
	private String size;
	
	private double price;
	
	private String description;
	
	private boolean isSpecial;

	public ListOfMealsDTO(int idFoodMenu, String typeMenu, String size, double price, String description,
			boolean isSpecial) {
		super();
		this.idFoodMenu = idFoodMenu;
		this.typeMenu = typeMenu;
		this.size = size;
		this.price = price;
		this.description = description;
		this.isSpecial = isSpecial;
	}
	
	
	public ListOfMealsDTO() {
		
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

	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	

}
