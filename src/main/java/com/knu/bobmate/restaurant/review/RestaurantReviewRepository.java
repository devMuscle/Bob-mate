package com.knu.bobmate.restaurant.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RestaurantReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<RestaurantReviewDto> findAllBy(int restaurantId) {
        String sql = "SELECT * " +
                "FROM RESTAURANT_REVIEW " +
                "WHERE Restaurant_id = ?";

        return jdbcTemplate.query(sql, new Object[]{restaurantId}, (rs, rowNum) -> {
            RestaurantReviewDto review = new RestaurantReviewDto();
            review.setId(rs.getInt(1));
            review.setScore(rs.getInt(2));
            review.setDescription(rs.getString(3));
            review.setRestaurantId(rs.getInt(4));
            review.setUserId(rs.getInt(5));
            return review;
        });
    }

    public void makeRestaurantReview(RestaurantReviewDto restaurantReviewDto) {
        String sql = "INSERT INTO RESTAURANT_REVIEW (Restaurant_review_id, Score, Description, Restaurant_id, User_id) " +
                "VALUES (restaurant_review_seq.nextval, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                restaurantReviewDto.getScore(),
                restaurantReviewDto.getDescription(),
                restaurantReviewDto.getRestaurantId(),
                restaurantReviewDto.getUserId()
        );
    }
}
