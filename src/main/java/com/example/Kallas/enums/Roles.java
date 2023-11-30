package com.example.Kallas.enums;

public enum Roles {

	ADMIN("Admin"),
	USER("User");
	
	private String role;

	Roles(String role) {
		this.role = role;
	}

	public String getRoles() {
		return role;
	}
}