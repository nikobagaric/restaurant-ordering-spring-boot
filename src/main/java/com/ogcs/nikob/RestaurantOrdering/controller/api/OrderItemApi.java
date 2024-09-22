package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.dto.OrderItemDTO;
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

@Tag(name = "OrderItem", description = "Order Item API")
public interface OrderItemApi {

    @Operation(
            summary = "Fetch all order items",
            description = "Fetches all order items and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))})
    })
    ResponseEntity<List<OrderItem>> getAllOrderItems();

    @Operation(
            summary = "Fetch specific order item",
            description = "Fetches specific order item via order item's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))})
    })
    ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id);

    @Operation(
            summary = "Create order item",
            description = "Creates order item given order item data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created order item",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))})
    })
    ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO);

    @Operation(
            summary = "Updates order item",
            description = "Updates order item given new order item data and the order item's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated order item",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))})
    })
    ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDetails);

    @Operation(
            summary = "Deletes order item",
            description = "Deletes order item given order item ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted order item",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItem.class))})
    })
    ResponseEntity<Void> deleteOrderItem(@PathVariable Long id);
}
