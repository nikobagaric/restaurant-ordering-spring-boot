package com.ogcs.nikob.RestaurantOrdering.repository;

import com.ogcs.nikob.RestaurantOrdering.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Restaurant} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and query methods.
 */


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
