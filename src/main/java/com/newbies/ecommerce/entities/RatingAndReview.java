package com.newbies.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RatingAndReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id" )
	@JsonBackReference(value = "user-rev")
	private Users user;
	@ManyToOne
	@JoinColumn(name="product_id")
	@JsonBackReference(value = "prod-rev")
	private Product product;
	private String reviewText;
	private double rating = 0;
	public RatingAndReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RatingAndReview(Long id, Users user, Product product, String reviewText, double rating) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.reviewText = reviewText;
		this.rating = rating;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	
}
