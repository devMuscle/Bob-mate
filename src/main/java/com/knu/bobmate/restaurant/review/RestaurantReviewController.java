package com.knu.bobmate.restaurant.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurants/reviews")
@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class RestaurantReviewController {

    private final RestaurantReviewService restaurantReviewService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<RestaurantReviewDto>> viewAllRestaurantReviewsBy(@PathVariable("restaurantId") int restaurantId) {
        return ResponseEntity.ok(restaurantReviewService.viewAllRestaurantReviewsBy(restaurantId));
    }

    @PostMapping("")
    public ResponseEntity<Void> makeRestaurantReview(@RequestBody RestaurantReviewDto restaurantReviewDto) {
        log.info(restaurantReviewDto.toString());
        restaurantReviewService.makeRestaurantReview(restaurantReviewDto);
        return ResponseEntity.ok(null);
    }
}
