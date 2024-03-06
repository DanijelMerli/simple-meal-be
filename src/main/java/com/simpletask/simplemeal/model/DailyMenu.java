package com.simpletask.simplemeal.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailyMenu implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDailyMenu;
	
	private Date dateMenu;
	
	private Meal regular;
	
	private Meal fit;
	
	private Extra soup;
	
	private Extra dessert;

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
