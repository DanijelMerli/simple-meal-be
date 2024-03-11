package com.simpletask.simplemeal.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "daily_menus")
public class DailyMenu implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date_Menu")
	private Date dateMenu;

	@ManyToOne
	@JoinColumn(name = "regular")
	private MainCourse regular;

	@ManyToOne
	@JoinColumn(name = "fit")
	private MainCourse fit;

	@ManyToOne
	@JoinColumn(name = "soup")
	private Extra soup;

	@ManyToOne
	@JoinColumn(name = "dessert")
	private Extra dessert;

	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "weekly_menu", nullable=false)
	private WeeklyMenu weeklyMenu;

	public DailyMenu() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int idDailyMenu) {
		this.id = idDailyMenu;
	}

	public Date getDate() {
		return dateMenu;
	}

	public void setDate(Date dateMenu) {
		this.dateMenu = dateMenu;
	}

	public MainCourse getRegular() {
		return regular;
	}

	public void setRegular(MainCourse regular) {
		this.regular = regular;
	}

	public MainCourse getFit() {
		return fit;
	}

	public void setFit(MainCourse fit) {
		this.fit = fit;
	}

	public Extra getExtrasSoup() {
		return soup;
	}

	public void setExtrasSoup(Extra soup) {
		this.soup = soup;
	}

	public Extra getDessert() {
		return dessert;
	}

	public void setDessert(Extra dessert) {
		this.dessert = dessert;
	}
	
	
	
}
