package com.simpletask.simplemeal.model;

import java.io.Serializable;

import com.simpletask.simplemeal.enums.MealType;

import jakarta.persistence.*;

@Entity
@Table(name = "meals")
@Inheritance(strategy = InheritanceType.JOINED)
public class Meal implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	public Meal() {
	}

	public Meal(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Meal(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int idFoodMenu) {
		this.id = idFoodMenu;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Meal{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
