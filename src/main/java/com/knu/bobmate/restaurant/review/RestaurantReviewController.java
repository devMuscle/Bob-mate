package com.knu.bobmate.restaurant.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<Void> makeRestaurantReview(@RequestBody RestaurantReviewDto restaurantReviewDto) {
        restaurantReviewService.makeRestaurantReview(restaurantReviewDto);
        return ResponseEntity.ok(null);
    }
}
