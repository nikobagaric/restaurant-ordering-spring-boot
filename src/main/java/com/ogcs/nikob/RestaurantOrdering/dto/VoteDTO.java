package com.ogcs.nikob.RestaurantOrdering.dto;

/**
 * Data Transfer Object for Vote.
 * Contains details about a vote including the user ID, poll ID, and restaurant ID.
 */


public class VoteDTO {
    private Long userId;
    private Long pollId;
    private Long restaurantId;

    public VoteDTO() {}

    public VoteDTO(Long userId, Long pollId, Long restaurantId) {
        this.userId = userId;
        this.pollId = pollId;
        this.restaurantId = restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
