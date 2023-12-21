package com.newbies.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newbies.ecommerce.entities.Product;
import com.newbies.ecommerce.entities.RatingAndReview;
import com.newbies.ecommerce.repositories.ProductRepository;
import com.newbies.ecommerce.repositories.RatingAndReviewRepository;
import com.newbies.ecommerce.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RatingAndReviewService {
	@Autowired
	private RatingAndReviewRepository ratingAndReviewRepo;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	public RatingAndReview createRatingAndReview(RatingAndReview ratingAndReview, long userID, long productId) {
		// TODO Auto-generated method stub
		if(userRepository.findById(userID).orElse(null) == null || productRepository.findById(productId).orElse(null) == null)
			return null;
		var alreadyRated = ratingAndReviewRepo.findBySameUserAndOnSameProduct(userID , productId);
		if(alreadyRated != null)
		{
			return null;
		}
//		Product ratedProduct = productRepository.findById(productId).orElse(null);
//		long counter = ratedProduct.getRatingAndReviewCount();
//		ratedProduct.setRatingAndReviewCount(++counter);
//		productRepository.save(ratedProduct);
//		
		System.out.println(ratingAndReview.getUser().getId());
		ratingAndReview.setUser(userRepository.findById(userID).orElse(null));
		return ratingAndReviewRepo.save(ratingAndReview);
		
		
		
	}

	public RatingAndReview updateRatingAndReview(RatingAndReview ratingAndReview, long userId, long productId) {
		// TODO Auto-generated method stub
		RatingAndReview alreadyRated = ratingAndReviewRepo.findBySameUserAndOnSameProduct(userId , productId);
		if(alreadyRated == null)
		{
			return null;
		}
//		Product ratedProduct = productRepository.findById(productId).orElse(null);
//		long counter = ratedProduct.getRatingAndReviewCount();
//		ratedProduct.setRatingAndReviewCount(++counter);
//		productRepository.save(ratedProduct);
		
		alreadyRated.setRating(ratingAndReview.getRating());
		alreadyRated.setReviewText(ratingAndReview.getReviewText());
		return ratingAndReviewRepo.save(alreadyRated);
		
		
	}
	@Transactional
	public String deleteRatingAndReview( long userId, long productId) {
		// TODO Auto-generated method stub
		
		ratingAndReviewRepo.deleteBySameUserAndOnSameProduct(userId , productId);
		
//		Product ratedProduct = productRepository.findById(productId).orElse(null);
//		long counter = ratedProduct.getRatingAndReviewCount();
//		ratedProduct.setRatingAndReviewCount(++counter);
//		productRepository.save(ratedProduct);
	
		return "Done" ;
		
		
	}
	

}
