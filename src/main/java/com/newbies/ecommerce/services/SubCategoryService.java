package com.newbies.ecommerce.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.entities.Category;
import com.newbies.ecommerce.entities.SubCategory;
import com.newbies.ecommerce.repositories.CategoryRepository;
import com.newbies.ecommerce.repositories.SubCategoryRepo;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepo subCategoryRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private CloudinaryUploadService cloudinaryUploadService;
	
	
	public Object createSubCategory(SubCategory subCategory, MultipartFile file) {
		// TODO Auto-generated method stub
		String imageString ="";
		try {
			Map data = cloudinaryUploadService.upload(file);
			imageString = data.get("secure_url").toString();
		} catch (IOException | Error e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		subCategory.setSubCatImage(imageString);
		return subCategoryRepo.save(subCategory);
		
	}

	public Object updateSubCategory(SubCategory subCategory) {
		// TODO Auto-generated method stub
		SubCategory existedSubCategory = subCategoryRepo.findById(subCategory.getId()).orElse(null);
		existedSubCategory.setCategory(subCategory.getCategory());
		existedSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
		return  subCategoryRepo.save(existedSubCategory);
	}

	public void deleteCategory(Long subCatId) {
		// TODO Auto-generated method stub
		subCategoryRepo.deleteById(subCatId);
		
	}

	public List<SubCategory> getAllSubCategories() {
		// TODO Auto-generated method stub
		List<SubCategory> allSubCategories = new ArrayList<>();
		subCategoryRepo.findAll().forEach(allSubCategories::add);
		return allSubCategories;
	}

	public List<SubCategory> getAllSubCategories(String categoryName) {
		// TODO Auto-generated method stub
		Category existedCategory = categoryRepo.findByCategoryName(categoryName);
		if(existedCategory == null)
		{
			System.out.println(existedCategory.getCategoryName());
			return null;
		}
		
		return subCategoryRepo.findAllByCategory(existedCategory.getId()) ;
	}
	public List<SubCategory> getAllSubCategories(long category) {
		// TODO Auto-generated method stub
	
		
		return subCategoryRepo.findAllByCategory(category);
	}

}
