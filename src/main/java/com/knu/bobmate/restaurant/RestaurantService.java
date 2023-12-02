package com.knu.bobmate.restaurant;

import com.knu.bobmate.restaurant.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantDto> viewRestaurants() {
        return restaurantRepository.viewRestaurants();
    }
}
