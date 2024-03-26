package com.simpletask.simplemeal.dto;

public class ChecksListUserDTO {

	private String firstName;

	private String lastName;

	private String email;

	private double priceForOrder;

	public ChecksListUserDTO(String firstName, String lastName, String email, double priceForOrder) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.priceForOrder = priceForOrder;
	}

	public ChecksListUserDTO() {
	}

	public String getFirstName() {
		return firstName;
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

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", price="
				+ priceForOrder + "]";
	}
}