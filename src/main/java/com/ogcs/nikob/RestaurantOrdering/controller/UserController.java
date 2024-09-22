package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.model.User;
import com.ogcs.nikob.RestaurantOrdering.service.UserService;
import com.ogcs.nikob.RestaurantOrdering.controller.api.UserApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link User}.
 * Provides endpoints to perform CRUD operations on users.
 * Implements the {@link UserApi} API doc interface.
 */


@RestController
@RequestMapping("/users")
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            User updatedUser = userService.saveUser(existingUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}