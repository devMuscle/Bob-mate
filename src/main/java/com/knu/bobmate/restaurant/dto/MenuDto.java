package com.knu.bobmate.restaurant.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MenuDto {
    private long id;
    private String name;
    private long price;
}
