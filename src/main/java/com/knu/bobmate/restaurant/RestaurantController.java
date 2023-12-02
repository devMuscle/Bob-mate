package com.knu.bobmate.restaurant;

import com.knu.bobmate.restaurant.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurants")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<List<RestaurantDto>> viewRestaurants() {
        return ResponseEntity.ok(restaurantService.viewRestaurants());
    }
}
