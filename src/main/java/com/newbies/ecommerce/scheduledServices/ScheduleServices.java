package com.newbies.ecommerce.scheduledServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.newbies.ecommerce.entities.RatingAndReview;
import com.newbies.ecommerce.repositories.ProductRepository;
import com.newbies.ecommerce.repositories.RatingAndReviewRepository;

@Component
public class ScheduleServices {

	@Autowired
	private RatingAndReviewRepository ratingAndReviewRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Scheduled(cron = "0 0 6,19 * * *")
	public void updateProductAvgRating() {
		productRepository.findAll().forEach((product )-> {
			long pid = product.getId();
			Double avgRating = ratingAndReviewRepository.getAvgRatingOfProduct(pid) == null ? 0 : ratingAndReviewRepository.getAvgRatingOfProduct(pid)  ;
			product.setRatingAndReviewAvg(avgRating);
			System.out.println(avgRating);
		});
	}
	
	
	
}
