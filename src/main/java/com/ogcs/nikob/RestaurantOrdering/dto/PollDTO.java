package com.ogcs.nikob.RestaurantOrdering.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for Poll; full use.
 * Contains details about a poll including creation and closing times, and associated votes.
 */


public class PollDTO {
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private List<VoteDTO> votes;

    public PollDTO() {}

    public PollDTO(LocalDateTime createdAt, LocalDateTime closedAt, List<VoteDTO> votes) {
        this.createdAt = createdAt;
        this.closedAt = closedAt;
        this.votes = votes;
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

    public List<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteDTO> votes) {
        this.votes = votes;
    }
}
