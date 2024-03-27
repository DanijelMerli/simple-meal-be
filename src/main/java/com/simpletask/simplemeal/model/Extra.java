package com.simpletask.simplemeal.model;

import com.simpletask.simplemeal.enums.ExtraType;

import jakarta.persistence.*;

@Entity
@Table(name = "extras")
@PrimaryKeyJoinColumn(name = "id_extra")
public class Extra extends Meal {

	@Column(name = "extra_type")
	private ExtraType extraType;

	private double price;

	public Extra() {

	}

	public Extra(int id, String name, String description, ExtraType extraType, double price) {
		super(id, name, description);
//		this.id = id;
		this.extraType = extraType;
		this.price = price;
	}

	public Extra(String name, String description, ExtraType extraType, double price) {
		super(name, description);
		this.extraType = extraType;
		this.price = price;
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

	@Override
	public String toString() {
		return "Extra{" + "id=" + super.getId() + ", name='" + super.getName() + '\'' + ", description='"
				+ super.getDescription() + '\'' + "extraType=" + extraType + ", price=" + price + '}';
	}
}
