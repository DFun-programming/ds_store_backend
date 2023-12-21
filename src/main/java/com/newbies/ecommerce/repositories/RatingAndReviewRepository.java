package com.newbies.ecommerce.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.newbies.ecommerce.entities.RatingAndReview;

import jakarta.transaction.Transactional;

@Transactional
public interface RatingAndReviewRepository extends CrudRepository<RatingAndReview, Long> {

	@Query("SELECT rar FROM RatingAndReview rar WHERE rar.user.id=:userId AND rar.product.id=:productId")
	RatingAndReview findBySameUserAndOnSameProduct(@Param("userId") long userId, @Param("productId")long productId);

	@Query("SELECT AVG(rar.rating) FROM RatingAndReview rar WHERE rar.product.id=:productId")
	 Double getAvgRatingOfProduct(@Param("productId")long pid);

	@Modifying
	@Query("Delete from RatingAndReview rar WHERE rar.user.id=:userId AND rar.product.id=:productId ")
	void deleteBySameUserAndOnSameProduct(@Param("userId") long userId, @Param("productId")long productId);

	
}
