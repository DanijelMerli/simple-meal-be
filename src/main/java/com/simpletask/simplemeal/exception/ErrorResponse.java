package com.simpletask.simplemeal.exception;


public class ErrorResponse {
    private String message;
    private int statusCode;
    
	public ErrorResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
    
    
}
