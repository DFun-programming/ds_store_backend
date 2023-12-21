package com.newbies.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newbies.ecommerce.entities.Category;
import com.newbies.ecommerce.services.CategoryService;

@RestController
@RequestMapping("/category/v1")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	

	@PostMapping("/createCategory")
	@PreAuthorize("hasRole('ROLE_admin')")
	ResponseEntity<Category> createCategory(@RequestBody Category cat){
		if(categoryService.createCategory(cat) == null)
		{
			return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
		}
		else {
			return new ResponseEntity<Category>(cat , HttpStatusCode.valueOf(201));
		}
	}
	@PutMapping("/updateCategory")
	@PreAuthorize("hasRole('ROLE_admin')")
	ResponseEntity<Category> updateCategory(@RequestBody Category cat){
		if(categoryService.updateCategory(cat) == null)
		{
			return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
		}
		else {
			return new ResponseEntity<Category>(cat , HttpStatusCode.valueOf(201));
		}
	}
	@DeleteMapping("/deleteCategory")
	@PreAuthorize("hasRole('ROLE_admin')")
	ResponseEntity<String> deleteCategory(@RequestParam Long catId){
		
		try {
			categoryService.deleteCategory(catId);
		
			return new ResponseEntity<>("Category Successfully deleted", HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error occurred while deleting . Error - "+e.getMessage() , HttpStatusCode.valueOf(401));
		}
	
	}
	
	@GetMapping("/allCategories")
	ResponseEntity<List<Category>> getAllCategories(){
		List<Category>allCategories =  categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(allCategories , HttpStatus.ACCEPTED);
	}
	@GetMapping("/getCategory")
	ResponseEntity <Category> getCategories(@RequestParam Long catId){
		Category cat =  categoryService.getAllCategoriesByCatId(catId);
		return new ResponseEntity<Category>(cat , HttpStatus.ACCEPTED);
	}
	
}
