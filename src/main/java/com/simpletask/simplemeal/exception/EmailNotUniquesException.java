package com.simpletask.simplemeal.exception;

public class EmailNotUniquesException extends RuntimeException{

	public EmailNotUniquesException(String message) {
        super(message);
    }
}
