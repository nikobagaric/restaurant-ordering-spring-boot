package com.ogcs.nikob.RestaurantOrdering.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents an order in the system.
 * Maps to the "orders" table with fields for ID, poll, user, restaurant, creation and sent times, and associated order items.
 */


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public Order() {}

    public Order(Long id, Poll poll, User user, Restaurant restaurant, LocalDateTime created_at, LocalDateTime sent_at) {
        this.id = id;
        this.poll = poll;
        this.user = user;
        this.restaurant = restaurant;
        this.createdAt = created_at;
        this.sentAt = sent_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.createdAt = created_at;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sent_at) {
        this.sentAt = sent_at;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
