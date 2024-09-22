package com.ogcs.nikob.RestaurantOrdering.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

import java.math.BigDecimal;

/**
 * Represents a menu item in a restaurant.
 * Maps to the "menu_items" table with fields for ID, restaurant, name, and price.
 */

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(precision = 2, name = "price", nullable = false)
    private BigDecimal price;


    public MenuItem() {}

    public MenuItem(Long id, Restaurant restaurant, String name, BigDecimal price) {
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
