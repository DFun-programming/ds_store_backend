package com.newbies.ecommerce.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.entities.Product;
import com.newbies.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CloudinaryUploadService cloudinaryUploadService ;
	
	public Product createProduct(Product product , MultipartFile image)
	{
		if(productRepo.findByProductname(product.getProductname()) != null)
			return null;
		try {
			Map data = cloudinaryUploadService.upload(image);
			String imageString = data.get("secure_url").toString();
			System.out.println(imageString);
			product.setProductImage(imageString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Error e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productRepo.save(product);
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> allProducts = new ArrayList<>();
		productRepo.findAll().forEach(allProducts::add);
		return allProducts;
	}

	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		Product existedProduct = productRepo.findById(product.getId()).orElse(product);
		if(existedProduct == null)
			return null;
		else {
			existedProduct.setProductname(product.getProductname());
			existedProduct.setProductDesc(product.getProductDesc());
			existedProduct.setProductPrice(product.getProductPrice());
	 		existedProduct.setSubcategory(product.getSubcategory());
	 		existedProduct.setCategory(product.getCategory());
	 		existedProduct.setCompanyName(product.getCompanyName());
		}
		return productRepo.save(existedProduct);
	
	}

	public void deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		productRepo.deleteById(productId);
	}

	public List<Product> getAllProductsByCategory(Long catId) {
		// TODO Auto-generated method stub
		List<Product>productsByCat =   productRepo.findAllByCategory(catId);
		return productsByCat;
	}
	/* ALL FILTERS  */
	public List<Product> getAllProductsBySubCategory(Long subCatId) {
		// TODO Auto-generated method stub
		List<Product>productsBySubCat =   productRepo.findAllBySubCategory(subCatId);
		return productsBySubCat;
	}

	public List<Product> getAllProductsByPriceOrder() {
		// TODO Auto-generated method stub
		List<Product>productsByOrderPrice =   productRepo.findByOrderByProductPriceAsc();
		return productsByOrderPrice;
	}

	public List<Product> getAllProductsByPriceOrderDsc() {
		// TODO Auto-generated method stub
		List<Product>productsByOrderPriceDsc =   productRepo.findByOrderByProductPriceDesc();
		return productsByOrderPriceDsc;
	}
}
