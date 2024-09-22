package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.model.User;

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

@Tag(name = "User", description = "User API")
public interface UserApi {

    @Operation(
            summary = "Fetch all users",
            description = "Fetches all users and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))})
    })
    ResponseEntity<List<User>> getAllUsers();

    @Operation(
            summary = "Fetch specific user",
            description = "Fetches specific user via user's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})
    })
    ResponseEntity<User> getUserById(@PathVariable Long id);

    @Operation(
            summary = "Create user",
            description = "Creates user given user data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})
    })
    ResponseEntity<User> createUser(@RequestBody User user);

    @Operation(
            summary = "Updates user",
            description = "Updates user given new user data and the user's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})
    })
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails);

    @Operation(
            summary = "Deletes user",
            description = "Deletes user given user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})
    })
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
