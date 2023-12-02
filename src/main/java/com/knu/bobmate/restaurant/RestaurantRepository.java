package com.knu.bobmate.restaurant;

import com.knu.bobmate.restaurant.dto.MenuDto;
import com.knu.bobmate.restaurant.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RestaurantRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<RestaurantDto> viewRestaurants() {
        String sql = "SELECT r.Restaurant_id, r.Name AS restaurantName, r.Location, " +
                "m.Menu_id, m.Name AS menuName, m.Price " +
                "FROM RESTAURANT r " +
                "LEFT JOIN MENU m ON r.Restaurant_id = m.Restaurant_id";

        Map<Long, RestaurantDto> restaurantMap = new HashMap<>();

        List<RestaurantDto> restaurants = jdbcTemplate.query(sql, new RowMapper<RestaurantDto>() {
            @Override
            public RestaurantDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long restaurantId = rs.getLong(1);

                RestaurantDto restaurant;
                if (!restaurantMap.containsKey(restaurantId)) {
                    restaurant = new RestaurantDto();
                    restaurant.setId(restaurantId);
                    restaurant.setName(rs.getString(2));
                    restaurant.setLocation(rs.getString(3));
                    restaurant.setMenus(new ArrayList<>());

                    restaurantMap.put(restaurantId, restaurant);
                } else {
                    restaurant = restaurantMap.get(restaurantId);
                }

                MenuDto menu = new MenuDto();
                menu.setId(rs.getInt(4));
                menu.setName(rs.getString(5));
                menu.setPrice(rs.getInt(6));

                restaurant.getMenus().add(menu);

                return restaurant;
            }
        });

        return new ArrayList<>(restaurantMap.values());
    }
}
