package com.ogcs.nikob.RestaurantOrdering.dto;

/**
 * Data Transfer Object for OrderItem.
 * Contains details for an order item including order ID, menu item ID, and quantity.
 */


public class OrderItemDTO {
    private Long orderId;
    private Long menuItemId;
    private Integer quantity;

    public OrderItemDTO() {}

    public OrderItemDTO(Long orderId, Long menuItemId, Integer quantity) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
