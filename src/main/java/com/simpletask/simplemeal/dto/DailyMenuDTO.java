package com.simpletask.simplemeal.dto;

import java.util.Date;


public class DailyMenuDTO {
	private int idDailyMenu;
	
	private Date dateMenu;
	
	private MealsDTO regular;
	
	private MealsDTO fit;
	
	private ExtraDTO soup;
	
	private ExtraDTO dessert;

	

	public DailyMenuDTO(int idDailyMenu, Date dateMenu, MealsDTO regular, MealsDTO fit, ExtraDTO soup,
			ExtraDTO dessert) {
		super();
		this.idDailyMenu = idDailyMenu;
		this.dateMenu = dateMenu;
		this.regular = regular;
		this.fit = fit;
		this.soup = soup;
		this.dessert = dessert;
	}

	public DailyMenuDTO() {
		
	}

	public int getIdDailyMenu() {
		return idDailyMenu;
	}

	public void setIdDailyMenu(int idDailyMenu) {
		this.idDailyMenu = idDailyMenu;
	}

	public Date getDateMenu() {
		return dateMenu;
	}

	public void setDateMenu(Date dateMenu) {
		this.dateMenu = dateMenu;
	}

	public MealsDTO getRegular() {
		return regular;
	}

	public void setRegular(MealsDTO regular) {
		this.regular = regular;
	}

	public MealsDTO getFit() {
		return fit;
	}

	public void setFit(MealsDTO fit) {
		this.fit = fit;
	}

	public ExtraDTO getSoup() {
		return soup;
	}

	public void setSoup(ExtraDTO soup) {
		this.soup = soup;
	}

	public ExtraDTO getDessert() {
		return dessert;
	}

	public void setDessert(ExtraDTO dessert) {
		this.dessert = dessert;
	}


	
	
	
	

}
