package com.newbies.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity
public class Order {
	
	private long amount;
	private String status;
	private long userId;
	private String razorpayOrderId;
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(long amount, String status, long userId, String razorpayOrderId) {
		super();
		this.amount = amount;
		this.status = status;
		this.userId = userId;
		this.razorpayOrderId = razorpayOrderId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	
	
	
	
}
