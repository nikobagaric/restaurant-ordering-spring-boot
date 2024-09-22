package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.dto.OrderDTO;
import com.ogcs.nikob.RestaurantOrdering.dto.OrderItemDTO;
import com.ogcs.nikob.RestaurantOrdering.model.Order;
import com.ogcs.nikob.RestaurantOrdering.model.OrderItem;

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
import java.util.Set;

@Tag(name = "Order", description = "Order API")
public interface OrderApi {

    @Operation(
            summary = "Fetch all orders",
            description = "Fetches all orders and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    ResponseEntity<List<Order>> getAllOrders();

    @Operation(
            summary = "Fetch specific order",
            description = "Fetches a specific order via order's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    ResponseEntity<Order> getOrderById(@PathVariable Long id);

    @Operation(
            summary = "Create order",
            description = "Creates an order given order data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created order",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    ResponseEntity<Order> createOrder(@RequestBody OrderDTO order);

    @Operation(
            summary = "Update order",
            description = "Updates an order given new order data and the order's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated order",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails);

    @Operation(
            summary = "Delete order",
            description = "Deletes an order given order ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted order",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);

    @Operation(
            summary = "Add item to order",
            description = "Adds an item to a specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item added to order",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    ResponseEntity<Order> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItem);

    @Operation(
            summary = "Fetch order items",
            description = "Fetches all items for a specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order items fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Set<OrderItem>> getOrderItems(@PathVariable Long orderId);

    @Operation(
            summary = "Delete order item",
            description = "Deletes a specific item from a specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order item deleted successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Order or order item not found",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId);
}
