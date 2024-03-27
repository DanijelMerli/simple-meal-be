package com.simpletask.simplemeal.dto;

import java.text.SimpleDateFormat;

import com.simpletask.simplemeal.model.DailyMenu;

public class DailyMenuDTO {
	private int idDailyMenu;

	private String dateMenu;

	private RegularMealDTO regular;

	private FitMealDTO fit;

	private ExtraDTO soup;

	private ExtraDTO dessert;

	public DailyMenuDTO(int idDailyMenu, String dateMenu, RegularMealDTO regular, FitMealDTO fit, ExtraDTO soup,
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

	public RegularMealDTO getRegular() {
		return regular;
	}

	public void setRegular(RegularMealDTO regular) {
		this.regular = regular;
	}

	public FitMealDTO getFit() {
		return fit;
	}

	public void setFit(FitMealDTO fit) {
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
		this.idDailyMenu = menu.getId();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		this.dateMenu = dateFormat.format(menu.getDate());
		this.fit = menu.getFit() != null ? new FitMealDTO(menu.getFit()) : null;
		this.regular = menu.getRegular() != null ? new RegularMealDTO(menu.getRegular()) : null;
		this.dessert = menu.getDessert() != null ? new ExtraDTO(menu.getDessert()) : null;
		this.soup = menu.getSoup() != null ? new ExtraDTO(menu.getSoup()) : null;
		this.idDailyMenu = menu.getId();
	}
}
