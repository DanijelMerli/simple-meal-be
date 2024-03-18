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
	private RegularMeal regular;

	@ManyToOne
	@JoinColumn(name = "fit")
	private FitMeal fit;

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

	public DailyMenu(int id, Date dateMenu, RegularMeal regular, FitMeal fit, Extra soup, Extra dessert, WeeklyMenu weeklyMenu) {
		this.id = id;
		this.dateMenu = dateMenu;
		this.regular = regular;
		this.fit = fit;
		this.soup = soup;
		this.dessert = dessert;
		this.weeklyMenu = weeklyMenu;
	}

	public DailyMenu(Date dateMenu, RegularMeal regular, FitMeal fit, Extra soup, Extra dessert, WeeklyMenu weeklyMenu) {
		this.dateMenu = dateMenu;
		this.regular = regular;
		this.fit = fit;
		this.soup = soup;
		this.dessert = dessert;
		this.weeklyMenu = weeklyMenu;
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

	public RegularMeal getRegular() {
		return regular;
	}

	public void setRegular(RegularMeal regular) {
		this.regular = regular;
	}

	public FitMeal getFit() {
		return fit;
	}

	public void setFit(FitMeal fit) {
		this.fit = fit;
	}

	public Extra getSoup() {
		return soup;
	}

	public void setSoup(Extra soup) {
		this.soup = soup;
	}

	public Extra getDessert() {
		return dessert;
	}

	public void setDessert(Extra dessert) {
		this.dessert = dessert;
	}

	@Override
	public String toString() {
		return "DailyMenu{" +
				"id=" + id +
				", dateMenu=" + dateMenu +
				", regular=" + regular +
				", fit=" + fit +
				", soup=" + soup +
				", dessert=" + dessert +
				", weeklyMenu=" + weeklyMenu +
				'}';
	}
}
