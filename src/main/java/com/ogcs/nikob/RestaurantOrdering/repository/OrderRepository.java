package com.ogcs.nikob.RestaurantOrdering.repository;

import com.ogcs.nikob.RestaurantOrdering.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Order} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and query methods.
 */


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
