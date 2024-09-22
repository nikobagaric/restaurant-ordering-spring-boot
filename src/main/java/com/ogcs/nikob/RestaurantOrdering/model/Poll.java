package com.ogcs.nikob.RestaurantOrdering.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a poll for selecting a restaurant.
 * Maps to the "polls" table with fields for ID, creation and closure dates, and relationships with votes and orders.
 */

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "closed_at", nullable = false)
    private LocalDateTime closedAt;

    @OneToMany(mappedBy = "poll")
    @JsonManagedReference
    private List<Vote> votes;

    @OneToOne(mappedBy = "poll")
    private Order order;

    public Poll() {
    }

    public Poll(Long id, LocalDateTime createdAt, LocalDateTime closedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
