package com.simpletask.simplemeal.dto;

public class ChecksListUserDTO {
	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private double priceForOrder;
	
	private boolean isPaid;


	public ChecksListUserDTO(int id, String firstName, String lastName, String email, double priceForOrder, boolean isPaid) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.priceForOrder = priceForOrder;
		this.isPaid = isPaid;
	}

	public ChecksListUserDTO() {
	}
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPriceForOrder() {
		return priceForOrder;
	}

	public void setPriceForOrder(double priceForOrder) {
		this.priceForOrder = priceForOrder;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", price="
				+ priceForOrder + "]";
	}
}
