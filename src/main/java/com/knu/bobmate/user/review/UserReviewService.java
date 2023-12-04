package com.knu.bobmate.user.review;

import com.knu.bobmate.user.review.dto.UserReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;

    public List<UserReviewDto> viewAllUserReviewsBy(int userId) {
        return userReviewRepository.findAllBy(userId);
    }

    public void makeUserReview(UserReviewDto userReviewDto) {
        userReviewRepository.makeUserReview(userReviewDto);
    }
}
