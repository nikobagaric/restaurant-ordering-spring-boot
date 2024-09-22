package com.ogcs.nikob.RestaurantOrdering.mapper;

import com.ogcs.nikob.RestaurantOrdering.dto.OrderItemDTO;
import com.ogcs.nikob.RestaurantOrdering.model.MenuItem;
import com.ogcs.nikob.RestaurantOrdering.model.OrderItem;
import com.ogcs.nikob.RestaurantOrdering.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Maps {@link OrderItemDTO} to {@link OrderItem} entity.
 * Uses {@link MenuItemService} to resolve {@link MenuItem} by ID.
 */


@Component
public class OrderItemMapper {

    @Autowired
    private MenuItemService menuItemService;

    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDTO.getQuantity());
        MenuItem menuItem = menuItemService.getMenuItemById(orderItemDTO.getMenuItemId()).orElse(null);
        orderItem.setMenuItem(menuItem);
        return orderItem;
    }
}
