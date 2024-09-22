package com.ogcs.nikob.RestaurantOrdering.repository;

import com.ogcs.nikob.RestaurantOrdering.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link OrderItem} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and query methods.
 */


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
