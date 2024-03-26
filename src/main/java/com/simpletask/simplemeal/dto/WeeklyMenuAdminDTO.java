package com.simpletask.simplemeal.dto;

import java.util.List;

import com.simpletask.simplemeal.model.WeeklyMenu;

public class WeeklyMenuAdminDTO {

	
	private List<DailyMenuAdminDTO> dailyMenus;
	
	private String startDate;
	
	private byte[] imageData;	
	
	

	public WeeklyMenuAdminDTO(List<DailyMenuAdminDTO> dailyMenus, String startDate, byte[] imageData) {
		super();
		this.dailyMenus = dailyMenus;
		this.startDate = startDate;
	}
	

	
	public WeeklyMenuAdminDTO() {}


	public List<DailyMenuAdminDTO> getDailyMenus() {
		return dailyMenus;
	}

	public void setDailyMenus(List<DailyMenuAdminDTO> dailyMenus) {
		this.dailyMenus = dailyMenus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	
	
	
}
