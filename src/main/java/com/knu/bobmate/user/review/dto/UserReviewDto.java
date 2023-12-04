package com.knu.bobmate.user.review.dto;

import lombok.Data;

@Data
public class UserReviewDto {
    private int id;
    private int score;
    private String description;
    private int revieweeId;
    private int reviewerId;
    private int reservationId;
}
