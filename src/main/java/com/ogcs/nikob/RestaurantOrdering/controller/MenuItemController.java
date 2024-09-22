package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.controller.api.MenuItemApi;
import com.ogcs.nikob.RestaurantOrdering.dto.MenuItemDTO;
import com.ogcs.nikob.RestaurantOrdering.model.MenuItem;
import com.ogcs.nikob.RestaurantOrdering.model.Restaurant;
import com.ogcs.nikob.RestaurantOrdering.repository.RestaurantRepository;
import com.ogcs.nikob.RestaurantOrdering.service.MenuItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link MenuItem}.
 * Provides endpoints to perform CRUD operations on menu items.
 * Implements the {@link MenuItemApi} API doc interface.
 */


@RestController
@RequestMapping("/menu-items")
public class MenuItemController implements MenuItemApi {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
        return menuItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        Restaurant restaurant = restaurantRepository.findById(menuItemDTO.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found!"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setRestaurant(restaurant);

        MenuItem savedMenuItem = menuItemService.saveMenuItem(menuItem);
        return ResponseEntity.ok(savedMenuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO menuItemDetails) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
        if (menuItem.isPresent()) {
            MenuItem existingMenuItem = menuItem.get();

            Restaurant restaurant = restaurantRepository.findById(menuItemDetails.getRestaurantId())
                            .orElseThrow(() -> new IllegalArgumentException("Restaurant not found!"));

            existingMenuItem.setRestaurant(restaurant);
            existingMenuItem.setName(menuItemDetails.getName());
            existingMenuItem.setPrice(menuItemDetails.getPrice());
            MenuItem updatedMenuItem = menuItemService.saveMenuItem(existingMenuItem);
            return ResponseEntity.ok(updatedMenuItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
        if (menuItem.isPresent()) {
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
