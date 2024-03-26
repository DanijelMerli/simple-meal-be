package com.simpletask.simplemeal.dto;

public class DailyMenuAdminDTO {
	
	private String dateMenu;
	private Integer regularMealId;
	private Integer fitMealId;
	private Integer soupId;
	private Integer dessertId;
	

	
	public DailyMenuAdminDTO(String dateMenu,Integer regularMealId, Integer fitMealId, Integer soupId, Integer dessertId) {
		super();
		this.dateMenu=dateMenu;
		this.regularMealId = regularMealId;
		this.fitMealId = fitMealId;
		this.soupId = soupId;
		this.dessertId = dessertId;
	}
	
	
	public DailyMenuAdminDTO() {}
	
	public Integer getRegularMealId() {
		return regularMealId;
	}
	public void setRegularMealId(int regularMealId) {
		this.regularMealId = regularMealId;
	}
	public Integer getFitMealId() {
		return fitMealId;
	}
	public void setFitMealId(int fitMealId) {
		this.fitMealId = fitMealId;
	}
	public Integer getSoupId() {
		return soupId;
	}
	public void setSoupId(Integer soupId) {
		this.soupId = soupId;
	}
	public Integer getDessertId() {
		return dessertId;
	}
	public void setDessertId(Integer dessertId) {
		this.dessertId = dessertId;
	}


	public String getDateMenu() {
		return dateMenu;
	}


	public void setDateMenu(String dateMenu) {
		this.dateMenu = dateMenu;
	}
	
	
}
