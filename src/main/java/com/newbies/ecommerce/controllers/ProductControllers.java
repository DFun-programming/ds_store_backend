package com.newbies.ecommerce.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newbies.ecommerce.entities.Product;
import com.newbies.ecommerce.services.CloudinaryUploadService;
import com.newbies.ecommerce.services.ProductService;

@RestController
@RequestMapping("/product/v1")
public class ProductControllers {

	@Autowired
	private ProductService productService;

	 
	
	@PostMapping(path = "/createProduct" , consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE })
	ResponseEntity<Product> createProducts( @RequestParam("productImage")MultipartFile image , @RequestPart("data") Product product ) {

		try {
		Product newProduct = productService.createProduct(product , image);
		if (newProduct == null){
			return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
		} else {

		}
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Product>(  HttpStatus.BAD_REQUEST);
		}
	}
	

	
	@PutMapping("/updateProduct")
	ResponseEntity<Product> updateProducts(@RequestBody Product product) {

		Product newProduct = productService.updateProduct(product);
		if (newProduct == null) {
			return new ResponseEntity<>(null, HttpStatusCode.valueOf(401));
		}

		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);

	}

	@GetMapping("/getAllProducts")
	ResponseEntity<List<Product>> getAllProducts() {
		List<Product> allProducts;
		allProducts = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(allProducts, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/deleteProduct")
	ResponseEntity<String> deleteProduct(@RequestParam Long productId )
	{
		try {
			productService.deleteProduct(productId);
			return new ResponseEntity<>("Product Successfully deleted", HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error occurred while deleting . Error - " +e.getMessage() , HttpStatusCode.valueOf(401));
		}
	}
	
	/* Filteration of products by category , subcategory , price */
	@GetMapping("/getAllProductsByCategory")
	ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam Long catId) {
		List<Product> allProductsByCat;
		allProductsByCat = productService.getAllProductsByCategory(catId);
		return new ResponseEntity<List<Product>>(allProductsByCat, HttpStatusCode.valueOf(200));
	}
	//By SubCategory
	@GetMapping("/getAllProductsBySubCategory")
	ResponseEntity<List<Product>> getAllProductsBySubCategory(@RequestParam Long subCatId) {
		List<Product> allProductsBySubCat;
		allProductsBySubCat = productService.getAllProductsBySubCategory(subCatId);
		return new ResponseEntity<List<Product>>(allProductsBySubCat, HttpStatusCode.valueOf(200));
	}
	
	//By Price-Asc
	@GetMapping("/getAllProductsByAscendingPrice")
	ResponseEntity<List<Product>> getAllProductsByPrice(){
		List<Product> allProductsByOrderPrice;
		allProductsByOrderPrice = productService.getAllProductsByPriceOrder();
		return new ResponseEntity<List<Product>>(allProductsByOrderPrice, HttpStatusCode.valueOf(200));
	}
	//By Price-Dsc
	@GetMapping("/getAllProductsByDescendingPrice")
	ResponseEntity<List<Product>> getAllProductsByPriceDsc(){
		List<Product> allProductsByOrderPriceDsc;
		allProductsByOrderPriceDsc = productService.getAllProductsByPriceOrderDsc();
		return new ResponseEntity<List<Product>>(allProductsByOrderPriceDsc, HttpStatusCode.valueOf(200));
	}
}
