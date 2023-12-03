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
            review.setId(rs.getInt(4));
            review.setParticipantId(rs.getInt(5));
            return review;
        });
    }
}
