package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.DailyMenu;
import com.simpletask.simplemeal.model.WeeklyMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class WeeklyMenuDTO {
	
	private int idWeeklyMenu;
	
	private List<DailyMenuDTO> dailyMenu;
	
	private String startDate;
	
	private byte[] imageData;

	public WeeklyMenuDTO() {
		
	}

	public WeeklyMenuDTO(int idWeeklyMenu, List<DailyMenuDTO> dailyMenu, String startDate) {
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public WeeklyMenuDTO(WeeklyMenu weekly) {
		this.idWeeklyMenu = weekly.getId();
		List<DailyMenuDTO> dailyMenuDTOs = new ArrayList<>();
		for (DailyMenu dm : weekly.getDailyMenu()) {
			dailyMenuDTOs.add(new DailyMenuDTO(dm));
		}
		this.dailyMenu = dailyMenuDTOs;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		this.startDate = dateFormat.format(weekly.getStartDate());
		if (weekly.getImage()!=null) //new
			this.imageData= weekly.getImage().getData();
	}
	
	

}
