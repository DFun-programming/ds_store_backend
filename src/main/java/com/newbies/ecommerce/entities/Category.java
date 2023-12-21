package com.newbies.ecommerce.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String categoryName;
	private String categoryDesc;

	@OneToMany(mappedBy = "category" , fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<SubCategory> subCategory;
	@OneToMany(mappedBy = "category" , fetch = FetchType.EAGER)
	@JsonManagedReference(value = "cat-prod")
	private List<Product> products;
	public Category(Long id, String categoryName, String categoryDesc, List<SubCategory> subCategory) 
	{
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.subCategory = subCategory;
	}
	public Category(Long id, String categoryName, String categoryDesc) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}
	public Category(Long id) {
		super();
		this.id = id;
	}

	public Category(Long id, String categoryName, String categoryDesc, List<SubCategory> subCategory,
			List<Product> products) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.subCategory = subCategory;
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public List<SubCategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

	
}
