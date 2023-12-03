package com.knu.bobmate.restaurant.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurants/reviews")
@RestController
public class RestaurantReviewController {

    private final RestaurantReviewService restaurantReviewService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<RestaurantReviewDto>> viewAllRestaurantReviewsBy(@PathVariable("restaurantId") int restaurantId) {
        return ResponseEntity.ok(restaurantReviewService.viewAllRestaurantReviewsBy(restaurantId));
    }
}
