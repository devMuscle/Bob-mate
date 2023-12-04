package com.knu.bobmate.user.review;

import com.knu.bobmate.restaurant.review.dto.RestaurantReviewDto;
import com.knu.bobmate.user.review.dto.UserReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<UserReviewDto> findAllBy(int userId) {

        String sql = "SELECT * " +
                "FROM USER_REVIEW " +
                "WHERE REVIEWEE_ID = ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            UserReviewDto review = new UserReviewDto();
            review.setId(rs.getInt(1));
            review.setScore(rs.getInt(2));
            review.setDescription(rs.getString(3));
            review.setRevieweeId(rs.getInt(4));
            review.setReviewerId(rs.getInt(5));
            review.setReservationId(rs.getInt(6));
            return review;
        });
    }

    public void makeUserReview(UserReviewDto userReviewDto) {

    }
}
