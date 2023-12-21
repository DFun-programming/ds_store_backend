package com.newbies.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newbies.ecommerce.entities.RatingAndReview;
import com.newbies.ecommerce.services.RatingAndReviewService;

@RestController
@RequestMapping("/rating/v1")
public class RatingAndReviewController {

	@Autowired
	private RatingAndReviewService ratingAndReviewService;
	
	@PostMapping("/createRatingAndReview/{productId}")
	ResponseEntity<RatingAndReview> createRatingAndReview(@RequestBody RatingAndReview ratingAndReview , @PathVariable long productId , @RequestParam long userId)
	{
		RatingAndReview newRatingAndReview = ratingAndReviewService.createRatingAndReview(ratingAndReview , userId,productId);
		if(	newRatingAndReview == null) {
				return new ResponseEntity<>(null,HttpStatusCode.valueOf(401));
		}
		return new ResponseEntity<RatingAndReview>(newRatingAndReview,HttpStatusCode.valueOf(201));
//		;
	}
	@PutMapping("/updateRatingAndReview/{productId}")
	ResponseEntity<RatingAndReview> updateRatingAndReview(@RequestBody RatingAndReview ratingAndReview , @PathVariable long productId , @RequestParam long userId)
	{
		RatingAndReview newRatingAndReview = ratingAndReviewService.updateRatingAndReview(ratingAndReview , userId,productId);
		if(newRatingAndReview == null)
			return new ResponseEntity<>(null,HttpStatusCode.valueOf(401));
		return new ResponseEntity<RatingAndReview>(newRatingAndReview,HttpStatusCode.valueOf(202));
//		
	}
	@DeleteMapping("/deleteRatingAndReview/{productId}")
	ResponseEntity<String> deleteRatingAndReview( @PathVariable long productId , @RequestParam long userId)
	{
		if(ratingAndReviewService.deleteRatingAndReview(userId,productId).equalsIgnoreCase("done")) 
		{
		return new ResponseEntity<String>("Deleted Successfully" ,HttpStatusCode.valueOf(200));
		}
		return new ResponseEntity<String>("An Error Occurred",HttpStatusCode.valueOf(401));
	}
	
}
