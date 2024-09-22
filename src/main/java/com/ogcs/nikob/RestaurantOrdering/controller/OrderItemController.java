package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.dto.OrderItemDTO;
import com.ogcs.nikob.RestaurantOrdering.model.MenuItem;
import com.ogcs.nikob.RestaurantOrdering.model.Order;
import com.ogcs.nikob.RestaurantOrdering.model.OrderItem;
import com.ogcs.nikob.RestaurantOrdering.repository.MenuItemRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.OrderRepository;
import com.ogcs.nikob.RestaurantOrdering.service.OrderItemService;
import com.ogcs.nikob.RestaurantOrdering.controller.api.OrderItemApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link OrderItem}.
 * Provides endpoints to perform CRUD operations on order items.
 * Implements the {@link OrderItemApi} API doc interface.
 */


@RestController
@RequestMapping("/order-items")
public class OrderItemController implements OrderItemApi {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        MenuItem menuItem = menuItemRepository.findById(orderItemDTO.getMenuItemId())
                .orElseThrow(() -> new IllegalArgumentException("MenuItem not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(orderItem.getQuantity());

        OrderItem savedOrder = orderItemService.saveOrderItem(orderItem);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDetails) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        if (orderItem.isPresent()) {
            OrderItem existingOrderItem = orderItem.get();

            Order order = orderRepository.findById(orderItemDetails.getOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));
            MenuItem menuItem = menuItemRepository.findById(orderItemDetails.getMenuItemId())
                    .orElseThrow(() -> new IllegalArgumentException("MenuItem not found"));

            existingOrderItem.setOrder(order);
            existingOrderItem.setMenuItem(menuItem);
            existingOrderItem.setQuantity(orderItemDetails.getQuantity());
            OrderItem updatedOrderItem = orderItemService.saveOrderItem(existingOrderItem);
            return ResponseEntity.ok(updatedOrderItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        if (orderItem.isPresent()) {
            orderItemService.deleteOrderItem(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
