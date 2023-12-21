package com.newbies.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;

import com.newbies.ecommerce.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByCategoryName(String categoryName);

	
	

}
