package com.newbies.ecommerce.entities;

public class ResetPass {
	private String password;
	private String newPassword;
	private String confirmNewPassword;
	
	public ResetPass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPass(String password, String newPassword, String confirmNewPassword) {
		super();
		this.password = password;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	
}
