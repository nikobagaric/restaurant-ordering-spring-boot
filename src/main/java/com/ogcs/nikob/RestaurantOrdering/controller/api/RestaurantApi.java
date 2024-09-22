package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.model.Restaurant;

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

@Tag(name = "Restaurant", description = "Restaurant API")
public interface RestaurantApi {

    @Operation(
            summary = "Fetch all restaurants",
            description = "Fetches all restaurants and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))})
    })
    ResponseEntity<List<Restaurant>> getAllRestaurants();

    @Operation(
            summary = "Fetch specific restaurant",
            description = "Fetches specific restaurant via restaurant's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))}),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))})
    })
    ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id);

    @Operation(
            summary = "Create restaurant",
            description = "Creates restaurant given restaurant data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created restaurant",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))})
    })
    ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant);

    @Operation(
            summary = "Update restaurant",
            description = "Updates restaurant given new restaurant data and the restaurant's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated restaurant",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))}),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Restaurant.class))})
    })
    ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurantDetails);

    @Operation(
            summary = "Delete restaurant",
            description = "Deletes restaurant given restaurant ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    ResponseEntity<Void> deleteRestaurant(@PathVariable Long id);
}
