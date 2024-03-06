package com.simpletask.simplemeal.dto;

import java.util.Date;
import java.util.List;


public class WeeklyMenuDTO {
	
	private int idWeeklyMenu;
	
	private List<DailyMenuDTO> dailyMenu;
	
	private Date startDate;

	public WeeklyMenuDTO() {
		
	}

	public WeeklyMenuDTO(int idWeeklyMenu, List<DailyMenuDTO> dailyMenu, Date startDate) {
		super();
		this.idWeeklyMenu = idWeeklyMenu;
		this.dailyMenu = dailyMenu;
		this.startDate = startDate;
	}

	public int getIdWeeklyMenu() {
		return idWeeklyMenu;
	}

	public void setIdWeeklyMenu(int idWeeklyMenu) {
		this.idWeeklyMenu = idWeeklyMenu;
	}

	public List<DailyMenuDTO> getDailyMenu() {
		return dailyMenu;
	}

	public void setDailyMenu(List<DailyMenuDTO> dailyMenu) {
		this.dailyMenu = dailyMenu;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	

}
