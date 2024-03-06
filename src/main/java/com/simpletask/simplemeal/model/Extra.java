package com.simpletask.simplemeal.model;

import java.io.Serializable;

import com.simpletask.simplemeal.enums.ExtraType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Extra implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExtra;
	
	private String name;
	
	private ExtraType extraType;
	
	private double price;

	public Extra() {
		
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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
