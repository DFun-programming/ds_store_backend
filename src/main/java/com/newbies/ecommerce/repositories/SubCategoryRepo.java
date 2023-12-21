package com.newbies.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.newbies.ecommerce.entities.SubCategory;

public interface SubCategoryRepo extends CrudRepository<SubCategory, Long> 
{

	@Query("SELECT sc FROM SubCategory sc WHERE sc.category.id=:categoryId")
	List<SubCategory> findAllByCategory(@Param("categoryId") Long id);

}
