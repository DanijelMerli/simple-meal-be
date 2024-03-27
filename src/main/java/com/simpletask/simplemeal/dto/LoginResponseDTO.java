package com.simpletask.simplemeal.dto;

public class LoginResponseDTO {

	private String token;
	
	public LoginResponseDTO() {
		super();
	}
	
	public LoginResponseDTO(String accessToken) {
		super();
		this.token = accessToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}