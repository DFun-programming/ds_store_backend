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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.entities.Category;
import com.newbies.ecommerce.entities.SubCategory;
import com.newbies.ecommerce.services.SubCategoryService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/category/v1")
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;

	@PostMapping("/createSubCategory")
	@PreAuthorize("hasRole('ROLE_admin')")
	ResponseEntity<String>createSubCategory(@RequestPart("data") SubCategory subCategory , @RequestParam("subCatImage") MultipartFile file )
	{
	
		if(subCategoryService.createSubCategory(subCategory , file) == null)
			return new ResponseEntity<String>("Error Occurred", HttpStatusCode.valueOf(401));
		return new ResponseEntity<String>("Done ", HttpStatusCode.valueOf(201));
	}
	@PutMapping("/updateSubCategory")
	@PreAuthorize("hasRole('ROLE_admin')")
	ResponseEntity<String>updateSubCategory(@RequestBody SubCategory subCategory)
	{
		
		if(subCategoryService.updateSubCategory(subCategory) == null)
			return new ResponseEntity<String>("Error Occurred ", HttpStatusCode.valueOf(401));
		return new ResponseEntity<String>("Done ", HttpStatusCode.valueOf(201));
	}
	@DeleteMapping("/deleteSubCategory")
	@PreAuthorize("hasRole('ROLE_admin')")
	ResponseEntity<String> deleteCategory(@RequestParam Long subCatId){
		
		try {
			subCategoryService.deleteCategory(subCatId);
		
			return new ResponseEntity<>("SubCategory Successfully deleted", HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error occurred while deleting . Error - " +e.getMessage() , HttpStatusCode.valueOf(401));
		}
	
	}
	@GetMapping("/getAllSubCategory")
	@PermitAll
	ResponseEntity<List<SubCategory>> allSubCategories(){
		List<SubCategory>allSubCategories =  subCategoryService.getAllSubCategories();
		return new ResponseEntity<List<SubCategory>>(allSubCategories , HttpStatus.ACCEPTED);
	}
	@PermitAll
	@GetMapping("/getAllSubCategory/categories")
	ResponseEntity<List<SubCategory>> allSubCategoriesByCatName(@RequestParam String categoryName){
		List<SubCategory>allSubCategories =  subCategoryService.getAllSubCategories(categoryName);
		if(allSubCategories == null)
		{
			return new ResponseEntity<List<SubCategory>>(allSubCategories , HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<SubCategory>>(allSubCategories , HttpStatus.ACCEPTED);
	}
	@PermitAll
	@GetMapping("/getAllSubCategory/categoryId")
	ResponseEntity<List<SubCategory>> allSubCategoriesByCatId(@RequestParam long category){
		List<SubCategory>allSubCategories =  subCategoryService.getAllSubCategories(category);
		if(allSubCategories == null)
		{
			return new ResponseEntity<List<SubCategory>>(allSubCategories , HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<SubCategory>>(allSubCategories , HttpStatus.ACCEPTED);
	}
	
}
