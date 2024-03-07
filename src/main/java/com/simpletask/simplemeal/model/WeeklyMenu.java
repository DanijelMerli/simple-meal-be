package com.simpletask.simplemeal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class WeeklyMenu implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idWeeklyMenu;

	@JsonIgnore
	@OneToMany(targetEntity = DailyMenu.class, mappedBy = "weeklyMenu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DailyMenu> dailyMenu;
	
	private Date startDate;

	public WeeklyMenu() {
		
	}

	public int getIdWeeklyMenu() {
		return idWeeklyMenu;
	}

	public void setIdWeeklyMenu(int idWeeklyMenu) {
		this.idWeeklyMenu = idWeeklyMenu;
	}

	public List<DailyMenu> getDailyMenu() {
		return dailyMenu;
	}

	public void setDailyMenu(List<DailyMenu> dailyMenu) {
		this.dailyMenu = dailyMenu;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
