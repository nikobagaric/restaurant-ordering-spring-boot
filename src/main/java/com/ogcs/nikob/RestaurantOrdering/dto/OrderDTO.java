package com.ogcs.nikob.RestaurantOrdering.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Order.
 * Contains details for an order including poll ID, user ID, restaurant ID, creation time, and sending time.
 */


public class OrderDTO {
    private Long pollId;
    private Long userId;
    private Long restaurantId;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

    public OrderDTO() {}

    public OrderDTO(Long pollId, Long userId, Long restaurantId, LocalDateTime createdAt, LocalDateTime sentAt) {
        this.pollId = pollId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
