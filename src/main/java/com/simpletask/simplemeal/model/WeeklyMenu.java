package com.simpletask.simplemeal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "weekly_menus")
public class WeeklyMenu implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "daily_Menu")
	@JsonIgnore
	@OneToMany(targetEntity = DailyMenu.class, mappedBy = "weeklyMenu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DailyMenu> dailyMenu;

	@Column(name = "start_Date")
	private Date startDate;

	public WeeklyMenu() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int idWeeklyMenu) {
		this.id = idWeeklyMenu;
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
