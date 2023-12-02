package com.knu.bobmate.restaurant.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
public class RestaurantDto {
    private long id;
    private String name;
    private String location;

    private List<MenuDto> menus = new ArrayList<>();

    public void addMenu(MenuDto menuDto) {
        this.menus.add(menuDto);
    }
}
