package com.simpletask.simplemeal.dto;

public class LoginResponseDTO {

	private String token;
//	private String refreshToken;
	
	public LoginResponseDTO() {
		super();
	}
	
	public LoginResponseDTO(String accessToken /*, String refreshToken */) {
		super();
		this.token = accessToken;
//		this.refreshToken = refreshToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

//	public String getRefreshToken() {
//		return refreshToken;
//	}
//
//	public void setRefreshToken(String refreshToken) {
//		this.refreshToken = refreshToken;
//	}
	
}