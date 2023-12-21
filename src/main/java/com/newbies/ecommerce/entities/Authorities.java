package com.newbies.ecommerce.entities;

import jakarta.persistence.Id;

//@Entity
public class Authorities {

	@Id
	private long id;
	private String role;
	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Authorities(long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
