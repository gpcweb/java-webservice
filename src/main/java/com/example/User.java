package com.example;

public class User {
	private Long id;
	private String userName;
	private String image;

	public User(Long id, String userName, String image) {
		this.id 	  = id;
		this.userName = userName;
		this.image    = image;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public Long getId(){
		return id;
	}

	public String getUserName(){
		return userName;
	}
	
	public String getImage(){
		return image;
	}

	public User() {
	}
}

