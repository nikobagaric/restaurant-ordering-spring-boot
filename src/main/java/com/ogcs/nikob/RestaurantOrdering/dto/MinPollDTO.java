package com.ogcs.nikob.RestaurantOrdering.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for Poll; full use.
 * Contains details about a poll including creation and closing times, and associated votes.
 */


public class MinPollDTO {
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;

    public MinPollDTO() {}

    public MinPollDTO(LocalDateTime createdAt, LocalDateTime closedAt) {
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }
}
