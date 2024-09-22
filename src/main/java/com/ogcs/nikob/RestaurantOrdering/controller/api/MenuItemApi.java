package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.dto.MenuItemDTO;
import com.ogcs.nikob.RestaurantOrdering.model.MenuItem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "MenuItem", description = "MenuItem API")
public interface MenuItemApi {

    @Operation(
            summary = "Fetch all menu items",
            description = "Fetches all menu items and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MenuItem.class))})
    })
    ResponseEntity<List<MenuItem>> getAllMenuItems();

    @Operation(
            summary = "Fetch specific menu item",
            description = "Fetches a specific menu item via menu item's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MenuItem.class))})
    })
    ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id);

    @Operation(
            summary = "Create menu item",
            description = "Creates a menu item given menu item data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created menu item",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MenuItem.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MenuItem.class))})
    })
    ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItemDTO menuItem);

    @Operation(
            summary = "Update menu item",
            description = "Updates a menu item given new menu item data and the menu item's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated menu item",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MenuItem.class))})
    })
    ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO menuItemDetails);

    @Operation(
            summary = "Delete menu item",
            description = "Deletes a menu item given menu item ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted menu item",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MenuItem.class))})
    })
    ResponseEntity<Void> deleteMenuItem(@PathVariable Long id);
}
