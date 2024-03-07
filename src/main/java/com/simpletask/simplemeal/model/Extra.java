package com.simpletask.simplemeal.model;

import java.io.Serializable;

import com.simpletask.simplemeal.enums.ExtraType;

import jakarta.persistence.*;

@Entity
public class Extra implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExtra;

	@Column(name = "name")
	private String name;

	private String description;
	
	private ExtraType extraType;
	
	private double price;

	public Extra() {
		
	}

	public Extra(int idExtra, String name, String description, ExtraType extraType, double price) {
		this.idExtra = idExtra;
		this.name = name;
		this.description = description;
		this.extraType = extraType;
		this.price = price;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdExtra() {
		return idExtra;
	}

	public void setIdExtra(int idExtra) {
		this.idExtra = idExtra;
	}

	public ExtraType getExtraType() {
		return extraType;
	}

	public void setExtraType(ExtraType extraType) {
		this.extraType = extraType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	

}
