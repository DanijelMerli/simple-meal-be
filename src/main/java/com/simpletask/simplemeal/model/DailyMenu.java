package com.simpletask.simplemeal.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class DailyMenu implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDailyMenu;
	
	private Date dateMenu;

	@ManyToOne
	@JoinColumn(name = "regular")
	private Meal regular;

	@ManyToOne
	@JoinColumn(name = "fit")
	private Meal fit;

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

	public int getIdDailyMenu() {
		return idDailyMenu;
	}

	public void setIdDailyMenu(int idDailyMenu) {
		this.idDailyMenu = idDailyMenu;
	}

	public Date getDate() {
		return dateMenu;
	}

	public void setDate(Date dateMenu) {
		this.dateMenu = dateMenu;
	}

	public Meal getRegular() {
		return regular;
	}

	public void setRegular(Meal regular) {
		this.regular = regular;
	}

	public Meal getFit() {
		return fit;
	}

	public void setFit(Meal fit) {
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
