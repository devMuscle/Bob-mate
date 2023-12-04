package com.knu.bobmate.user.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import com.knu.bobmate.user.review.dto.UserReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users/reviews")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserReviewController {

    private final UserReviewService userReviewService;

    @GetMapping("")
    public ResponseEntity<List<UserReviewDto>> viewAllUserReviewsBy(@RequestAttribute int userId) {
        return ResponseEntity.ok(userReviewService.viewAllUserReviewsBy(userId));
    }

    @PostMapping("")
    public ResponseEntity<Void> makeUserReview(@RequestAttribute int userId, @RequestBody UserReviewDto userReviewDto) {
        userReviewDto.setReviewerId(userId);
        userReviewService.makeUserReview(userReviewDto);
        return ResponseEntity.ok(null);
    }
}
