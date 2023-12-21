package com.newbies.ecommerce.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newbies.ecommerce.entities.Category;
import com.newbies.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	public Category createCategory(Category cat) {
		// TODO Auto-generated method stub
		return categoryRepo.save(cat);
		
		
	}
	public Category updateCategory(Category cat) {
		// TODO Auto-generated method stub
		Category existedCategory = categoryRepo.findById(cat.getId()).orElse(null);
		existedCategory.setCategoryName(cat.getCategoryName());
		existedCategory.setCategoryDesc(cat.getCategoryDesc());
		return categoryRepo.save(existedCategory);
		
	}
	public void deleteCategory(Long catId) {
		// TODO Auto-generated method stub
		Category exCategory =  categoryRepo.findById(catId).orElse(null);
		categoryRepo.delete(exCategory);
	
		
	}
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> allCategories = new ArrayList<>();
		categoryRepo.findAll().forEach(allCategories::add);
		return allCategories;
	}
	public Category getAllCategoriesByCatId(Long catId) {
		// TODO Auto-generated method stub
		return categoryRepo.findById(catId).orElse(null);
		
	}

}
