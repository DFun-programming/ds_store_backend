package com.newbies.ecommerce.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String phnNo;
	private String address;
	private String landmark;
	private String image;
	private String accountType;
	private String country;
	private String state;
	private String pincode;
	private String otp;
	@OneToMany(mappedBy = "user" , cascade =CascadeType.REMOVE ,orphanRemoval = true )
	@JsonManagedReference(value = "user-rev")
	private List<RatingAndReview> ratingAndReview;
	
//	@OneToOne
//	private Otp otp;
//	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Users(Long id, String name, String email, String password, String phnNo, String address, String landmark,
			String image, String accountType, String otp) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.landmark = landmark;
		this.image = image;
		this.accountType = accountType;
		this.otp = otp;
	}

	

	public Users(Long id, String name, String email, String password, String phnNo, String address, String landmark,
			String image, String accountType, String country, String state, String pincode, String otp,
			List<RatingAndReview> ratingAndReview) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.landmark = landmark;
		this.image = image;
		this.accountType = accountType;
		this.country = country;
		this.state = state;
		this.pincode = pincode;
		this.otp = otp;
		this.ratingAndReview = ratingAndReview;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public List<RatingAndReview> getRatingAndReview() {
		return ratingAndReview;
	}


	public void setRatingAndReview(List<RatingAndReview> ratingAndReview) {
		this.ratingAndReview = ratingAndReview;
	}


	public Users(String email) {
		super();
		this.email = email;
	}

	

	public Users(Long id) {
		super();
		this.id = id;
	}


	public Users(String name, String email, String password, String phnNo, String address, String landmark,
			String image, String accountType ,String otp) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.landmark = landmark;
		this.image = image;
		this.accountType = accountType;
		this.otp = otp;
	}


	public Users(Long id, String name, String email, String password, String phnNo, String address, String accountType,
			String otp) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.accountType = accountType;
		this.otp = otp;
	}


	public Users(String name, String email, String password, String phnNo, String address, String accountType,
			String otp) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phnNo = phnNo;
		this.address = address;
		this.accountType = accountType;
		this.otp = otp;
	}

	
	

	public Users(String email, String password , String otp) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhnNo() {
		return phnNo;
	}
	public void setPhnNo(String phnNo) {
		this.phnNo = phnNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


	

//	public Otp getOtp() {
//		return otp;
//	}
//
//
//	public void setOtp(Otp otp) {
//		this.otp = otp;
//	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phnNo=" + phnNo
				+ ", address=" + address + ", landmark=" + landmark + ", image=" + image + ", accountType="
				+ accountType + ", otp=" + otp + ", ratingAndReview=" + ratingAndReview + "]";
	}
	
	
	
}
