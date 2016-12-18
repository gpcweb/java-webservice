package com.example;

public class CustomApiError {

	private String message;
	
	public CustomApiError(String message) {
		this.message = message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public CustomApiError(){
		
	}
	
}