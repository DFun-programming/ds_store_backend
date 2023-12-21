package com.newbies.ecommerce.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productname;
	private String productDesc;
	private Double productPrice;
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference(value = "cat-prod")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	@JsonBackReference(value = "subcat-prod")
	private SubCategory subcategory;
	private String companyName;
	@OneToMany(mappedBy = "product" , fetch = FetchType.EAGER)
	@JsonManagedReference(value = "prod-rev")
	private List<RatingAndReview> ratingAndReview;
	private double ratingAndReviewAvg;
	private String productImage;
	private int quantity;
	private List<String> features;
	private List<String> tags;

	public Product(Long id, String productname, String productDesc, Double productPrice, Category category,
			SubCategory subcategory, String companyName, List<RatingAndReview> ratingAndReview) {
		super();
		this.id = id;
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		this.ratingAndReview = ratingAndReview;
	}
	

	public Product(String productname, String productDesc, Double productPrice, Category category,
			SubCategory subcategory, String companyName, int quantity, List<String> features,
			List<String> tags) {
		super();
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		
		this.quantity = quantity;
		this.features = features;
		this.tags = tags;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public List<String> getFeatures() {
		return features;
	}


	public void setFeatures(List<String> features) {
		this.features = features;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Product(Long id) {
		super();
		this.id = id;
	}

	public Product(Long id, String productname, String productDesc, Double productPrice, Category category,
			SubCategory subcategory, String companyName, List<RatingAndReview> ratingAndReview,
			double ratingAndReviewAvg) {
		super();
		this.id = id;
		this.productname = productname;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.subcategory = subcategory;
		this.companyName = companyName;
		this.ratingAndReview = ratingAndReview;
		this.ratingAndReviewAvg = ratingAndReviewAvg;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public SubCategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<RatingAndReview> getRatingAndReview() {
		return ratingAndReview;
	}

	public void setRatingAndReview(List<RatingAndReview> ratingAndReview) {
		this.ratingAndReview = ratingAndReview;
	}

	public double getRatingAndReviewAvg() {
		return ratingAndReviewAvg;
	}

	public void setRatingAndReviewAvg(double ratingAndReviewAvg) {
		this.ratingAndReviewAvg = ratingAndReviewAvg;
	}
	


	
	

}
