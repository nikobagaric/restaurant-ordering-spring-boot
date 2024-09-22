package com.ogcs.nikob.RestaurantOrdering.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object for MenuItem.
 * Contains details for a menu item including restaurant ID, name, and price.
 */


public class MenuItemDTO {
    private Long restaurantId;
    private String name;
    private BigDecimal price;

    public MenuItemDTO() {}

    public MenuItemDTO(Long restaurantId, String name, BigDecimal price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
