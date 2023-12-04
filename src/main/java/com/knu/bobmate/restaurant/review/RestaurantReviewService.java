package com.knu.bobmate.restaurant.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantReviewService {

    private final RestaurantReviewRepository restaurantReviewRepository;

    public List<RestaurantReviewDto> viewAllRestaurantReviewsBy(int restaurantId) {
        return restaurantReviewRepository.findAllBy(restaurantId);
    }

    public void makeRestaurantReview(RestaurantReviewDto restaurantReviewDto) {
        restaurantReviewRepository.makeRestaurantReview(restaurantReviewDto);
    }
}
