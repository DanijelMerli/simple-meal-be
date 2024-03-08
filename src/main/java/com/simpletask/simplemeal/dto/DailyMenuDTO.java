package com.simpletask.simplemeal.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.simpletask.simplemeal.model.DailyMenu;


public class DailyMenuDTO {
	private int idDailyMenu;
	
	private String dateMenu;
	
	private MealsDTO regular;
	
	private MealsDTO fit;
	
	private ExtraDTO soup;
	
	private ExtraDTO dessert;

	

	public DailyMenuDTO(int idDailyMenu, String dateMenu, MealsDTO regular, MealsDTO fit, ExtraDTO soup,
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

	public String getDateMenu() {
		return dateMenu;
	}

	public void setDateMenu(String dateMenu) {
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


	public DailyMenuDTO(DailyMenu menu) {
		this.idDailyMenu=menu.getIdDailyMenu();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		this.dateMenu= dateFormat.format(menu.getDate());
		this.fit= menu.getFit() != null ? new MealsDTO(menu.getFit()) : null;
		this.regular= menu.getRegular() != null ? new MealsDTO(menu.getRegular()) : null;
		this.dessert=menu.getDessert() != null ? new ExtraDTO(menu.getDessert()) : null;
		this.soup= menu.getExtrasSoup() != null ? new ExtraDTO(menu.getExtrasSoup()) : null;
		this.idDailyMenu = menu.getIdDailyMenu();
	}
	
	
	

}
