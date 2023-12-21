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

@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subCategoryName;
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference
	private Category category;
	@OneToMany(mappedBy = "subcategory", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "subcat-prod")
	private List<Product> products;
	private String subCatImage;
	private String subCategoryDesc;

	public SubCategory(Long id, String subCategoryName, List<Product> products) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.products = products;
	}

	public SubCategory(Long id, String subCategoryName, Category category, List<Product> products, String subCatImage,
			String subCategoryDesc) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.products = products;
		this.subCatImage = subCatImage;
		this.subCategoryDesc = subCategoryDesc;
	}

	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}

	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}

	public SubCategory(Long id, String subCategoryName, Category category, List<Product> products) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.products = products;
	}

	public SubCategory(Long id) {
		super();
		this.id = id;
	}

	public SubCategory(Long id, String subCategoryName, Category category, List<Product> products, String subCatImage) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.products = products;
		this.subCatImage = subCatImage;
	}

	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getSubCatImage() {
		return subCatImage;
	}

	public void setSubCatImage(String subCatImage) {
		this.subCatImage = subCatImage;
	}

}
