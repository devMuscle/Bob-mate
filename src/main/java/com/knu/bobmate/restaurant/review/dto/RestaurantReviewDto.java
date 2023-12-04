package com.knu.bobmate.restaurant.review.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RestaurantReviewDto {
    private int id;
    private int score;
    private String description;
    private int restaurantId;
    private int userId;
}
