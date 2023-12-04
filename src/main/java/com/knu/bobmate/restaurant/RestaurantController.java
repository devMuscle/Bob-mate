package com.knu.bobmate.restaurant;

import com.knu.bobmate.restaurant.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<List<RestaurantDto>> viewRestaurants() {
        return ResponseEntity.ok(restaurantService.viewRestaurants());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> viewRestaurant(@PathVariable("restaurantId") int restaurantId) {
        RestaurantDto restaurantDto = restaurantService.viewRestaurant(restaurantId);
        log.info(restaurantId + " ");
        return ResponseEntity.ok(restaurantDto);
    }
}
