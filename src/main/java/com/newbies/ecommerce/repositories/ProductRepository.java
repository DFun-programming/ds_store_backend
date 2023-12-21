package com.newbies.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.newbies.ecommerce.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByProductname(String productname);

	@Query("SELECT p FROM Product p WHERE p.category.id=:catId")
	List<Product> findAllByCategory(@Param("catId")Long catId);
	
	@Query("SELECT p FROM Product p WHERE p.subcategory.id=:subcatId")
	List<Product> findAllBySubCategory(@Param("subcatId")Long subcatId);

	List<Product> findByOrderByProductPriceAsc();

	List<Product> findByOrderByProductPriceDesc();

	
	
}
