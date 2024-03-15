package com.simpletask.simplemeal.dto;

import com.simpletask.simplemeal.model.Extra;

public class ExtraDTO {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private String extraType;
	
	private double price;

	public ExtraDTO() {
		
	}

	public ExtraDTO(int idExtra, String name, String extraType, double price, String description) {
		super();
		this.id = idExtra;
		this.name = name;
		this.description = description;
		this.extraType = extraType;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtraType() {
		return extraType;
	}

	public void setExtraType(String extraType) {
		this.extraType = extraType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExtraDTO(Extra extra) {
		this.id = extra.getId();
		this.extraType = extra.getExtraType().toString();
		this.name = extra.getName();
		this.description = extra.getDescription();
		this.price = extra.getPrice();
	}
	
	
	
	

}
