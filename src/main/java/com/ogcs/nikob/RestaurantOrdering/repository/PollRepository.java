package com.ogcs.nikob.RestaurantOrdering.repository;

import com.ogcs.nikob.RestaurantOrdering.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for {@link Poll} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and query methods.
 */


@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByClosedAtBeforeAndOrderIsNull(LocalDateTime now);
}
