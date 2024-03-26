package com.simpletask.simplemeal.dto;

import java.util.List;

public class ChosenOneDTO {

	private List<ChecksListUserDTO> users;

	private double totalPrice;

	public ChosenOneDTO() {
	}

	public ChosenOneDTO(List<ChecksListUserDTO> users, double totalPrice) {
		super();
		this.users = users;
		this.totalPrice = totalPrice;
	}

	public List<ChecksListUserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<ChecksListUserDTO> users) {
		this.users = users;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
