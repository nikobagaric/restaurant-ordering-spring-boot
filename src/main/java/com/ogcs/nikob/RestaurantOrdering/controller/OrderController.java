package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.controller.api.MenuItemApi;
import com.ogcs.nikob.RestaurantOrdering.dto.OrderDTO;
import com.ogcs.nikob.RestaurantOrdering.dto.OrderItemDTO;
import com.ogcs.nikob.RestaurantOrdering.mapper.OrderItemMapper;
import com.ogcs.nikob.RestaurantOrdering.model.*;
import com.ogcs.nikob.RestaurantOrdering.repository.PollRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.RestaurantRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.UserRepository;
import com.ogcs.nikob.RestaurantOrdering.service.OrderService;
import com.ogcs.nikob.RestaurantOrdering.service.OrderItemService;
import com.ogcs.nikob.RestaurantOrdering.controller.api.OrderApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing {@link Order}.
 * Provides endpoints to perform CRUD operations on orders.
 * Implements the {@link OrderApi} API doc interface.
 */

@RestController
@RequestMapping("/orders")
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        // Validate and retrieve entities based on provided IDs
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Poll poll = pollRepository.findById(orderDTO.getPollId())
                .orElseThrow(() -> new IllegalArgumentException("Poll not found"));
        Restaurant restaurant = restaurantRepository.findById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        // Create and save the order
        Order order = new Order();
        order.setUser(user);
        order.setPoll(poll);
        order.setRestaurant(restaurant);
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setSentAt(orderDTO.getSentAt());

        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(savedOrder);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setPoll(orderDetails.getPoll());
            existingOrder.setUser(orderDetails.getUser());
            existingOrder.setRestaurant(orderDetails.getRestaurant());
            existingOrder.setCreatedAt(orderDetails.getCreatedAt());
            existingOrder.setSentAt(orderDetails.getSentAt());
            Order updatedOrder = orderService.saveOrder(existingOrder);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<Order> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            OrderItem orderItem = orderItemMapper.toEntity(orderItemDTO);
            if (orderItem.getMenuItem() != null) {
                orderItem.setOrder(existingOrder);
                OrderItem savedOrderItem = orderItemService.saveOrderItem(orderItem);
                existingOrder.getOrderItems().add(savedOrderItem); // Make sure Order class has a collection of OrderItems
                orderService.saveOrder(existingOrder); // Save the updated order
                return ResponseEntity.ok(existingOrder);
            } else {
                return ResponseEntity.badRequest().build(); // Handle invalid OrderItemDTO
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<Set<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(value -> ResponseEntity.ok(value.getOrderItems()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            Optional<OrderItem> orderItem = orderItemService.getOrderItemById(orderItemId);
            if (orderItem.isPresent() && existingOrder.getOrderItems().remove(orderItem.get())) {
                orderItemService.deleteOrderItem(orderItemId);
                orderService.saveOrder(existingOrder);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
