package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.controller.api.RestaurantApi;
import com.ogcs.nikob.RestaurantOrdering.model.Restaurant;
import com.ogcs.nikob.RestaurantOrdering.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Restaurant}.
 * Provides endpoints to perform CRUD operations on restaurants.
 * Implements the {@link RestaurantApi} API doc interface.
 */


@RestController
@RequestMapping("/restaurants")
public class RestaurantController implements RestaurantApi {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurant
            (@PathVariable Long id, @RequestBody Restaurant restaurantDetails) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if(restaurant.isPresent()) {
            Restaurant existingRestaurant = restaurant.get();
            existingRestaurant.setName(restaurantDetails.getName());
            existingRestaurant.setAddress(restaurantDetails.getAddress());
            Restaurant updatedRestaurant = restaurantService.saveRestaurant(existingRestaurant);
            return ResponseEntity.ok(updatedRestaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if (restaurant.isPresent()) {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
